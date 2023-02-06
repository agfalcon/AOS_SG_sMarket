package com.smilestone.smarket.Retrofit

import android.util.Log
import android.widget.Toast
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.CODE_OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectService {
    var loginData:Login? = null
    var signUpData:SignUp? = null
    //초기 retrofit 빌드
    //TODO("SERVER BASEURL 입력")
    val retrofit = Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    //로그인 서비스
    val loginService: LoginService = retrofit.create(LoginService::class.java)
    fun login(id: String, pw: String): Int? {
        var code: Int? = null
        loginService.requestLogin(id, pw)
            .enqueue(object: Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginData = response.body()
                    code = CODE_OK
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    code = CODE_FAIL
                }
            })
        return code
    }

    //회원가입 서비스
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)
    fun signUp(id: String, pw: String, email:String, nickname: String): Int? {
        var code: Int? = null
        signUpService.requestSignUp(id, pw, email, nickname)
            .enqueue(object: Callback<SignUp>{
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    signUpData = response.body()
                    code = CODE_OK
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.e("SignUp", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }

}