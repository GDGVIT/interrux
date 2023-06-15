package com.example.interrux

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

class AuthInterceptor(
    private val _context: Context,
    private val refreshAuthToken: ((String?) -> String)?
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val unAuthReq = chain.request()
        val token = getAccessToken(_context)
        val response = chain.proceed(makeNewRequest(token, unAuthReq))

        if(response.code==HttpURLConnection.HTTP_UNAUTHORIZED){
            synchronized(this){
                val newToken = getAccessToken(_context)
                if (newToken != token) {
                    response.close()
                    return chain.proceed(makeNewRequest(newToken, unAuthReq))
                }else{
                    if(refreshAuthToken!=null) {
                        val updatedAccessToken = getUpdateAuthToken(_context, refreshAuthToken)
                        response.close()
                        return chain.proceed(makeNewRequest(updatedAccessToken, unAuthReq))
                    }
                }


            }
        }

        return response

    }

    private fun makeNewRequest(newToken: String, unAuthReq: Request): Request {
        return unAuthReq.newBuilder()
            .addHeader("Authorization", "Bearer $newToken")
            .build()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    }

    private fun getAccessToken(context: Context): String {
        return getSharedPreferences(context).getString("access_token", "") ?: ""
    }

    private fun getRefreshToken(context: Context): String {
        return getSharedPreferences(context).getString("refresh_token", "") ?: ""
    }

    private fun getUpdateAuthToken(context: Context, refreshAuthToken: (String?) -> String): String {
        val refreshToken = getRefreshToken(context)
        val newAccessToken = refreshAuthToken(refreshToken)
        saveTokens(context, newAccessToken, refreshToken)
        return newAccessToken
    }

    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
        getSharedPreferences(context).edit()
            .putString("access_token", accessToken)
            .putString("refresh_token", refreshToken)
            .apply()
    }
}