package com.smilestone.smarket.retrofit

import com.smilestone.smarket.dto.EditData
import com.smilestone.smarket.dto.Product
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