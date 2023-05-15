package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val header_name: String, private val header_value: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .build()
        val response =  chain.proceed(request)

        val modifiedResponse = response.newBuilder()
            .addHeader(header_name, header_value)
            .build()

        return modifiedResponse


    }
}