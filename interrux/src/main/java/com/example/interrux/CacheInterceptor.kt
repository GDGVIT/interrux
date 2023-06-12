package com.example.interrux

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor(private val days: Int = 1) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val cacheControl = CacheControl.Builder()
            .maxAge(days, TimeUnit.DAYS)
            .build()
        val response = chain.proceed(request)

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

}