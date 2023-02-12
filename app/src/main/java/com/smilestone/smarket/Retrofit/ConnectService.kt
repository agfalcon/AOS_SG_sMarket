package com.smilestone.smarket.Retrofit

import android.util.Log
import android.widget.Toast
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.Retrofit.ConnectService.homeService
import com.smilestone.smarket.Retrofit.ConnectService.productData
import com.smilestone.smarket.STATUS_OK
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

object ConnectService {
    var loginData:Login? = null
    var signUpData:SignUp? = null
    var productData: ArrayList<Product>? = null
    var itemData: Product? = null
    //초기 retrofit 빌드
    val retrofit = Retrofit.Builder().baseUrl("http://52.78.175.29:8088")
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
                .baseUrl("http://52.78.175.29:8088")
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
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
                    code = response.code()
                    productData = response.body()
                    Log.d("서버 통신", productData.toString())
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
                    Log.d("통신", productData.toString())
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
               title: String="",
               content: String="",
               price: Long=0,
               ): Int?{
        var code: Int? = null
        val editData = EditData(sellerId, title, content, price)
        homeService.uploadProduct(editData)
            .enqueue(object: Callback<Product>{
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code = response.code()
                    Log.d("확인용", response.body().toString())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Upload", t.message.toString())
                    code = CODE_FAIL
                }

            })
        return code
    }


    //글 보기 서비스
    fun item(id:Long){
        var code: Int? = null
        homeService.getItem(id).enqueue(
            object: Callback<Product>{
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code = response.code()
                    itemData = response.body()
                    Log.d("확인용", itemData.toString())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Item", t.message.toString())
                }

            }
        )
    }

}