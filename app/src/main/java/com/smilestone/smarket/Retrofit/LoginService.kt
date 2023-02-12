package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("/api/signin")
    fun requestLogin(
        @Field("userId") id:String,
        @Field("password") pw:String
    ): Call<Login>

    @GET("api/users/singin")
    fun requestJWTLogin(
        @Header("Authorization") token: String
    ) : Call<Login>
}