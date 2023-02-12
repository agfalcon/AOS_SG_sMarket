package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
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