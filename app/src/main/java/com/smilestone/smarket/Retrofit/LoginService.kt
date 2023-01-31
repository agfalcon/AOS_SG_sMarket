package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun requestLogin(
        @Field("id") id:String,
        @Field("pw") pw:String
    ): Call<Login>
}