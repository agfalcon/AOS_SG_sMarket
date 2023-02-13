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

data class Product(
    @SerializedName("productId")
    val productId: Long,

    @SerializedName("sellerId")
    val sellerId: Long,

    @SerializedName("buyerId")
    val buyerId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("price")
    val price: Long,

    @SerializedName("state")
    val state: Boolean,

    @SerializedName("view")
    val view: Long,

    @SerializedName("localDateTime")
    val localDateTime: String,

)

data class EditData(
    @SerializedName("sellerId")
    val sellerId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("price") val price: Long
    )