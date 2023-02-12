package com.smilestone.smarket.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.Home.HomeActivity
import com.smilestone.smarket.LOGIN_TOKEN
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.SignUp.SignupActivity
import com.smilestone.smarket.TOKEN
import com.smilestone.smarket.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var model: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnSingup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnLogin.setOnClickListener {
            //loginAccess()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.editId.doAfterTextChanged {
            model.loginData.value?.id = binding.editId.text.toString()
            //TODO("아이디 양식 체크")
        }

        binding.editPw.doAfterTextChanged {
            model.loginData.value?.pw = binding.editPw.text.toString()
            //TODO("비밀번호 양식 체크")
        }
    }

    private fun loginAccess(){
        val loginPreferences: SharedPreferences = getSharedPreferences(LOGIN_TOKEN, Context.MODE_PRIVATE)
        if(loginPreferences==null || loginPreferences.getString(TOKEN, "").equals("")){
            val result = model.login()
            if(result == 1){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                saveToken()
                finish()
            }
        }
        else{
            val result = model.jwtLogin(loginPreferences.getString(TOKEN,""))
            if(result == 1){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun saveToken() {
        with(getSharedPreferences(LOGIN_TOKEN, Context.MODE_PRIVATE).edit()){
            putString(TOKEN, ConnectService.loginData?.token)
            apply()
        }
    }
}