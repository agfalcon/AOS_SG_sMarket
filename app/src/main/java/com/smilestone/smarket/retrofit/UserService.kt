package com.smilestone.smarket.retrofit

import com.smilestone.smarket.dto.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserService {
    @GET("/api/users")
    fun getUser(
        @Header("Authorization") token : String,
        @Query("id") id: Long
    ) : Call<UserData>
}