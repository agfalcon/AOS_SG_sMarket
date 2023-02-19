package com.smilestone.smarket.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.home.HomeActivity
import com.smilestone.smarket.LOGIN_TOKEN
import com.smilestone.smarket.signup.SignupActivity
import com.smilestone.smarket.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var model: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[LoginViewModel::class.java]
        val loginPreferences: SharedPreferences = getSharedPreferences(LOGIN_TOKEN, Context.MODE_PRIVATE)
        Log.d("토큰", loginPreferences.getString(LOGIN_TOKEN,"").toString())
        if(loginPreferences!=null && !loginPreferences.getString(LOGIN_TOKEN, "").equals("")){
            model.jwtLogin(loginPreferences.getString(LOGIN_TOKEN,""))
        }

        binding.btnSingup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnLogin.setOnClickListener {
            loginAccess()
        }

        binding.editId.doAfterTextChanged {
            model.loginData.value?.id = binding.editId.text.toString()
        }

        binding.editPw.doAfterTextChanged {
            model.loginData.value?.pw = binding.editPw.text.toString()
        }

        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result == 1){
                saveToken()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun loginAccess() {
        model.login()
    }

    private fun saveToken() {
        with(getSharedPreferences(LOGIN_TOKEN, Context.MODE_PRIVATE).edit()){
            putString(LOGIN_TOKEN, model.loginMessage?.value?.tokens?.accessToken.toString())
            apply()
        }
    }
}