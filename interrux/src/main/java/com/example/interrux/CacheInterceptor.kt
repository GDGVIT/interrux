package com.example.interrux

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(private val cacheSize: Long) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val _request = chain.request()
        val _cacheRequest = _request.newBuilder()
            .addHeader("Cache-Control", "max-age=$cacheSize")
        val request = _cacheRequest.build()
        return chain.proceed(request)
    }

}