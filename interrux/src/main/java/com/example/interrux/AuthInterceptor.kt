package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val unauthReq = chain.request()
        val authorizedRequest = unauthReq.newBuilder()
            .addHeader("Authorization", "Bearer $authToken")
        val request = authorizedRequest.build()
        return chain.proceed(request)
    }
}