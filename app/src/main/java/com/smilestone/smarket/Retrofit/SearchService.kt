package com.smilestone.smarket.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST


interface SearchService {

    @GET("/home")
    fun SearchAll(): Call<Post>

    @POST("/search")
    fun seach(
        @Field("keyword") keyword:String
    ):Call<Post>
}