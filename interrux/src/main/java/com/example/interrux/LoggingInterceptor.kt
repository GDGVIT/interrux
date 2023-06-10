package com.example.interrux

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.nio.charset.Charset

class LoggingInterceptor: Interceptor {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("LoggingInterceptor", "Request: ")
        loggingInterceptor.intercept(chain)
        val response = chain.proceed(request)
        Log.d("LoggingInterceptor", "Response: ")
        response.close()
        loggingInterceptor.intercept(chain)
        return response
    }
}