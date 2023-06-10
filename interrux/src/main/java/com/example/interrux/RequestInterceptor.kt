package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val headerName: String, private val headerValue: String) :
    Interceptor {
    init {
        require(headerName.isNotBlank()) { "Header name cannot be blank" }
        require(headerValue.isNotBlank()) { "Header value cannot be blank" }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader(headerName, headerValue)
            .build()
        return chain.proceed(modifiedRequest)
    }
}