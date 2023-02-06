package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface SignUpService {

    @POST("SignUp")
    fun requestSignUp(
        @Field("id") id:String,
        @Field("pw") pw:String,
        @Field("email") email:String,
        @Field("nickname") nickname:String,
    ) : Call<SignUp>
}