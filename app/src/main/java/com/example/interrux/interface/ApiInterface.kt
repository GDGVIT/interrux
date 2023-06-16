package com.example.interrux.`interface`

import com.example.interrux.model.AuthTestingModel
import com.example.interrux.model.LoginReq
import com.example.interrux.model.LoginResponse
import com.example.interrux.model.RefreshRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @GET("auth/profile")
    fun getProfile(): Call<AuthTestingModel>


    @POST("auth/login")
    fun login(@Body loginReq: LoginReq): Call<LoginResponse>

    @POST("auth/refresh-token")
    fun refreshToken(@Body refreshRequest: RefreshRequest): Call<LoginResponse>


}


