package com.smilestone.smarket.Retrofit

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectService {
    var loginData:Login? = null
    //초기 retrofit 빌드
    //TODO("SERVER BASEURL 입력")
    val retrofit = Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    //로그인 서비스
    val loginService: LoginService = retrofit.create(LoginService::class.java)
    fun login(id: String, pw: String): Int? {
        var code: Int? = null
        ConnectService.loginService.requestLogin(id, pw)
            .enqueue(object: Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginData = response.body()
                    code = 1000
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    code = 1001
                }
            })
        return code
    }

}