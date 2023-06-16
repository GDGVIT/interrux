package com.dscvit.interrux.model

data class LoginReq(
    val email: String,
    val password: String,
)

data class RefreshRequest(
    val refreshToken: String,
)