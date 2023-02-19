package com.smilestone.smarket.Login

import android.app.Application
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.*
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.dto.Login


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    data class inputData(var id: String, var pw: String)


    private val _loginData = MutableLiveData<inputData>()
    private val _code = MutableLiveData<Int>()
    private val _loginMessage = MutableLiveData<Login>()

    val loginData : LiveData<inputData>
        get() = _loginData

    val code: LiveData<Int>
        get() = _code

    val loginMessage : LiveData<Login>
        get() = _loginMessage


    init{
       _loginData.value = inputData("","")
    }

    fun login(){
        if(!checkLogin()){
            return
        }
        ConnectService.login(_loginData.value?.id.toString(), _loginData.value?.pw.toString(), _code, _loginMessage) ?: -1
    }

    fun jwtLogin(token: String?){
        ConnectService.jwtLogin(token,_code, _loginMessage)
    }

    fun checkCode(): Int{
        Log.d("로그인", _loginMessage.value.toString())
        val result = when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK ->{
                1
            }
            REQUSET_ERROR, NO_AUTHORIZATION->{
                Toast.makeText(getApplication(), "잘못된 로그인 정보입니다", Toast.LENGTH_SHORT).show()
                -1
            }
            else -> {
                Toast.makeText(getApplication(), "로그인 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }


    private fun checkLogin(): Boolean{
        if(_loginData.value?.id?.isEmpty() == true || _loginData.value?.pw?.isEmpty() == true){
            Toast.makeText(getApplication(), "아이디와 패스워드를 모두 채워주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}