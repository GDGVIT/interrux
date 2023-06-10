package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class RequestResponseInterceptor(
    private val requestHeaderName: String, private val requestHeaderValue: String,
    private val responseHeaderName: String, private val responseHeaderValue: String,
) : Interceptor {
    init {
        require(requestHeaderName.isNotBlank()) { "Request header name cannot be blank" }
        require(requestHeaderValue.isNotBlank()) { "Request header value cannot be blank" }
        require(responseHeaderName.isNotBlank()) { "Response header name cannot be blank" }
        require(responseHeaderValue.isNotBlank()) { "Response header value cannot be blank" }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader(requestHeaderName, requestHeaderValue)
            .build()
        val response = chain.proceed(modifiedRequest)
        val modifiedResponse = response.newBuilder()
            .addHeader(responseHeaderName, responseHeaderValue)
            .build()

        return modifiedResponse
    }
}
