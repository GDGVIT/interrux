package com.dscvit.interrux.`interface`

import com.dscvit.interrux.model.AuthTestingModel
import com.dscvit.interrux.model.LoginReq
import com.dscvit.interrux.model.LoginResponse
import com.dscvit.interrux.model.RefreshRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("auth/profile")
    fun getProfile(): Call<AuthTestingModel>


    @POST("auth/login")
    fun login(@Body loginReq: LoginReq): Call<LoginResponse>

    @POST("auth/refresh-token")
    fun refreshToken(@Body refreshRequest: RefreshRequest): Call<LoginResponse>


}


