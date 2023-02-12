package com.smilestone.smarket.Retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ProductService {

    @GET("/api/product/list/all")
    fun requestProducts(): Call<ArrayList<Product>>

    @GET("/api/product/title")
    fun requestSearch(
        @Query("title") title : String
    ): Call<ArrayList<Product>>


    @POST("/api/product/post")
    fun uploadProduct(
        @Body() params: EditData
    ):Call<Product>

    @GET("api/product/id")
    fun getItem(
        @Query("productId") productId: Long
    ) : Call<Product>
}