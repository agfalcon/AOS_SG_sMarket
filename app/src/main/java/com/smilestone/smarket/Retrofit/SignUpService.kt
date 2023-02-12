package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpService {

    @FormUrlEncoded
    @POST("/api/signup")
    fun requestSignUp(
        @Field("userId") id:String,
        @Field("password") pw:String,
        @Field("nickName") nickname:String,
    ) : Call<SignUp>
}