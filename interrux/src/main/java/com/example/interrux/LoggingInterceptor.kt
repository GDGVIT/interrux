package com.example.interrux

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor : Interceptor {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request details
        Log.d("LoggingInterceptor", "Request URL: ${request.url}")
        Log.d("LoggingInterceptor", "Request Method: ${request.method}")
        Log.d("LoggingInterceptor", "Request Headers: ${request.headers}")
        Log.d("LoggingInterceptor", "Request Body: ${request.body}")

        // Log the response details
        val response = loggingInterceptor.intercept(chain)
        Log.d("LoggingInterceptor", "Response Code: ${response.code}")
        Log.d("LoggingInterceptor", "Response Headers: ${response.headers}")
        Log.d("LoggingInterceptor", "Response Body: ${response.body}")

        return response
    }
}
