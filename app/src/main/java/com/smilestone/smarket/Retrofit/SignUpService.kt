package com.smilestone.smarket.Retrofit

import com.smilestone.smarket.dto.SignUp
import com.smilestone.smarket.dto.SignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpService {


    @POST("/api/signup")
    fun requestSignUp(
       @Body() params: SignUpData
    ) : Call<SignUp>
}