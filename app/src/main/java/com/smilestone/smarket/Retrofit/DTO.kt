package com.smilestone.smarket.Retrofit

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("token")
    val token: String,
)

data class SignUp(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)

data class Post(
    @SerializedName("title")
    val title: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("price")
    val price: String,
)