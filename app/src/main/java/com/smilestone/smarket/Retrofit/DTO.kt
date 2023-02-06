package com.smilestone.smarket.Retrofit

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)

data class SignUp(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)