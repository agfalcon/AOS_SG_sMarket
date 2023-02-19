package com.smilestone.smarket.Retrofit

import com.smilestone.smarket.dto.Login
import com.smilestone.smarket.dto.LoginData
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @POST("/api/signin")
    fun requestLogin(
        @Body() params: LoginData
    ): Call<Login>

    @GET("api/users/signin")
    fun requestJWTLogin(
        @Header("Authorization") token: String
    ) : Call<Login>
}