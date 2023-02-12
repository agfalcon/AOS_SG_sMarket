package com.smilestone.smarket.Retrofit

import android.util.Log
import android.widget.Toast
import com.smilestone.smarket.CODE_FAIL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectService {
    var loginData:Login? = null
    var signUpData:SignUp? = null
    var productData: ArrayList<Product>? = null
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
                    code = response.code()
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    code = CODE_FAIL
                }
            })
        return code
    }

    //JWT 로그인 서비스
    fun jwtLogin(token:String?): Int?{
        val jwtClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(token)).build()
        val retrofit: Retrofit by lazy {
            Retrofit.Builder().client(jwtClient)
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        var code: Int? = null
        val jwtLoginService: LoginService = retrofit.create(LoginService::class.java)
        jwtLoginService.requestJWTLogin(token?:"")
            .enqueue(object : Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginData = response.body()
                    code = response.code()
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
        signUpService.requestSignUp(id, pw, nickname)
            .enqueue(object: Callback<SignUp>{
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    signUpData = response.body()
                    code = response.code()
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.e("SignUp", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }

    //홈 화면 서비스
    val homeService: ProductService = retrofit.create(ProductService::class.java)
    fun home(): Int?{
        var code: Int? = null
        homeService.requestProducts()
            .enqueue(object: Callback<ArrayList<Product>>{
                override fun onResponse(call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
                    code = response.code()
                    productData = response.body()
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("Home", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }


    //검색 서비스
    fun search(title: String): Int?{
        var code: Int? = null
        homeService.requestSearch(title)
            .enqueue(object: Callback<ArrayList<Product>>{
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
                    code = response.code()
                    productData = response.body()
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("Search", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }

    //글 올리기 서비스
    fun upload(sellerId: Long=0,
               buyerId: Long=0,
               title: String="",
               content: String="",
               price: Long=0,
               state: Boolean=false,
               view: Long=0,
               localDateTime: String=""): Int?{
        var code: Int? = null
        homeService.uploadProduct(sellerId, buyerId, title, content, price, state, view, localDateTime)
            .enqueue(object: Callback<Product>{
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code = response.code()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Upload", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }

}