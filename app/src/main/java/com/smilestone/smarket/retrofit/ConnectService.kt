package com.smilestone.smarket.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.dto.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectService {
    var signUpData: SignUp? = null

    //초기 retrofit 빌드
    val retrofit = Retrofit.Builder().baseUrl("http://52.78.175.29:8088")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //로그인 서비스
    val loginService: LoginService = retrofit.create(LoginService::class.java)
    fun login(id: String, pw: String, code: MutableLiveData<Int>, loginMessage: MutableLiveData<Login>){
        val data = LoginData(id, pw)
        loginService.requestLogin(data)
            .enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginMessage.value = response.body()
                    code.value = response.code()
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("로그인", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //JWT 로그인 서비스
    fun jwtLogin(token: String?, id: String?, code: MutableLiveData<Int>, loginMessage: MutableLiveData<Login>) {
        val jwtClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(token)).build()
        val retrofit: Retrofit by lazy {
            Retrofit.Builder().client(jwtClient)
                .baseUrl("http://52.78.175.29:8088")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        val jwtLoginService: LoginService = retrofit.create(LoginService::class.java)
        jwtLoginService.requestJWTLogin(token ?: "", id ?: "")
            .enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginMessage.value = response.body()
                    code.value = response.code()
                    Log.d("로그인", code.value.toString())
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //회원가입 서비스
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)
    fun signUp(id: String, pw: String, nickname: String, code: MutableLiveData<Int>) {
        val data = SignUpData(id, pw, nickname)
        signUpService.requestSignUp(data)
            .enqueue(object : Callback<SignUp> {
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    signUpData = response.body()
                    code.value = response.code()
                    Log.d("SignUp확인", response.toString())
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.d("SignUp확인", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }


    //홈 화면 서비스
    val homeService: ProductService = retrofit.create(ProductService::class.java)
    fun home(code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        homeService.requestProducts()
            .enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                    //Log.d("테스트", post.value?.get(0).toString())
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.e("Home", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }


    //검색 서비스
    fun search(title: String, code: MutableLiveData<Int>, post:MutableLiveData<ArrayList<Product>>){
        Log.d("테스트", title)
        homeService.requestSearch(title)
            .enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>
                ) {
                    code.value = response.code()
                    post.value = response.body()
                    Log.d("테스트", response.body().toString())
                }

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.d("테스트", t.message.toString())
                    code.value = CODE_FAIL
                }
            })
    }

    //글 올리기 서비스
    fun upload(
        sellerId: Long = 0,
        title: String = "",
        content: String = "",
        price: Long = 0,
        code: MutableLiveData<Int>
    ){
        val editData = EditData(sellerId, title, content, price)
        homeService.uploadProduct(editData)
            .enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code.value = response.code()
                    Log.d("확인용", response.body().toString())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Upload", t.message.toString())
                    code.value = CODE_FAIL
                }

            })
    }


    //글 보기 서비스
    fun item(id: Long, code: MutableLiveData<Int>, product: MutableLiveData<Product>) {
        homeService.getItem(id.toLong()).enqueue(
            object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    code.value = response.code()
                    product.value = response.body()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e("Item", t.message.toString())
                }

            }
        )
    }
}