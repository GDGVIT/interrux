package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authToken: String,
    private val tokenRefreshListener: TokenRefreshListener?,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val unAuthReq = chain.request()
        var newToken = authToken

        if (tokenRefreshListener != null) {
            newToken = try {
                tokenRefreshListener.onTokenRefresh()
            } catch (e: Exception) {
                throw IllegalStateException("Token refresh failed.", e)
            }
        }

        val authorizedRequest = unAuthReq.newBuilder()
            .addHeader("Authorization", "Bearer $newToken")
            .build()

        return chain.proceed(authorizedRequest)

    }


    interface TokenRefreshListener {
        fun onTokenRefresh(): String
    }
}