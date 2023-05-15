package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val header_name: String, private val header_value: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val unchangedRequest = chain.request()
        val modifiedRequest = unchangedRequest.newBuilder()
            .addHeader(header_name, header_value)
            .build()
        return chain.proceed(modifiedRequest)
    }
}