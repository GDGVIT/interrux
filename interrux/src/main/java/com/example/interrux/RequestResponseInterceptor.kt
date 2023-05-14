package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class RequestResponseInterceptor(private val request_header_name: String , private val request_header_value: String,
     private val response_header_name: String, private val response_header_value: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val unchangedRequest = chain.request()
        val modifiedRequest = unchangedRequest.newBuilder()
                    .addHeader(request_header_name, request_header_value)
                    .build()
        val response =  chain.proceed(modifiedRequest)
        val modifiedResponse = response.newBuilder()
            .addHeader(response_header_name, response_header_value)
            .build()

        return modifiedResponse

    }
}