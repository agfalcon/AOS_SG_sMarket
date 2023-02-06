package com.smilestone.smarket.SignUp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.CODE_OK
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.Retrofit.SignUp

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    data class userData(var id: String, var pw: String, var email: String, var nickname: String)

    private val _userData:userData = userData("","","","")
    private val _signUpData = MutableLiveData<userData>()
    val signUpData: LiveData<userData>
        get() = _signUpData

    init{
        _signUpData.value = _userData
    }

    fun signUp(): Int{
        if(!checkData())
            return -1
        val code:Int? = ConnectService.signUp(_signUpData.value?.id.toString(),_signUpData.value?.pw.toString(),
        _signUpData.value?.email.toString(), _signUpData.value?.nickname.toString())
        val result = when(code){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "회원가입 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            CODE_OK ->{
                if(ConnectService.loginData?.code=="1000"){
                    1
                } else {
                    Toast.makeText(getApplication(), "잘못된 회원가입 정보입니다.", Toast.LENGTH_SHORT).show()
                    -1
                }
            }
            else -> {
                Toast.makeText(getApplication(), "회원가입 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }

    private fun checkData(): Boolean{
        if(_signUpData.value?.id?.isEmpty() == true || _signUpData.value?.pw?.isEmpty() == true ||
            _signUpData.value?.email?.isEmpty() == true || _signUpData.value?.nickname?.isEmpty() == true ){
            Toast.makeText(getApplication(), "회원가입 양식을 모두 채워주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}