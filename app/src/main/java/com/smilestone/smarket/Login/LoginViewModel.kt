package com.smilestone.smarket.Login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.REQUSET_ERROR
import com.smilestone.smarket.REQUSET_OK
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.Retrofit.Login


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    data class inputData(var id: String, var pw: String)


    private val _loginData = MutableLiveData<inputData>()

    val loginData : LiveData<inputData>
        get() = _loginData


    init{
       _loginData.value = inputData("","")
    }

    fun login(): Int{
        if(!checkLogin()){
            return -1
        }
        val code: Int? = ConnectService.login(_loginData.value?.id.toString(), _loginData.value?.pw.toString()) ?: -1
        val result = when(code){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            REQUSET_OK ->1
            REQUSET_ERROR->{
                Toast.makeText(getApplication(), ConnectService.loginData?.message.toString(), Toast.LENGTH_SHORT).show()
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