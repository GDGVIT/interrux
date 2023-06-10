package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val headerName: String, private val headerValue: String) :
    Interceptor {
    init {
        require(headerName.isNotBlank()) { "Header name cannot be blank" }
        require(headerValue.isNotBlank()) { "Header value cannot be blank" }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().build()
        val response = chain.proceed(request)

        val modifiedResponse = response.newBuilder()
            .addHeader(headerName, headerValue)
            .build()

        return modifiedResponse
    }
}
