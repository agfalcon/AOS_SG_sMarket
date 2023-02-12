package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {

    @GET("/products")
    fun requestProducts(): Call<ArrayList<Product>>

    @GET("/products/title")
    fun requestSearch(
        @Field("title") title : String
    ): Call<ArrayList<Product>>

    @POST("/product")
    fun uploadProduct(
        @Field("sellerId")
        sellerId: Long,
        @Field("buyerId")
        buyerId: Long,
        @Field("title")
        title: String,
        @Field("content")
        content: String,
        @Field("price")
        price: Long,
        @Field("state")
        state: Boolean,
        @Field("view")
        view: Long,
        @Field("localDateTime")
        localDateTime: String
    ):Call<Product>
}