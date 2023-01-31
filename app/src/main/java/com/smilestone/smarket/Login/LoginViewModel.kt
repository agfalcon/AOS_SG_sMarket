package com.smilestone.smarket.Login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.Retrofit.Login


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        //TODO("LOGIN_SERVER_URL 및 양식")
        const val LOGIN_SERVER_URL = ""
    }

    private val _id = MutableLiveData<String>()
    private val _pw = MutableLiveData<String>()

    val id : LiveData<String>
        get() = _id
    val pw : LiveData<String>
        get() = _pw

    fun login(): Int{
        val code: Int? = ConnectService.login(_id.value.toString(), _pw.value.toString()) ?: -1
        val result = when(code){
            -1, 1001 -> {
                Toast.makeText(getApplication(), "로그인 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            1000 ->{
                if(ConnectService.loginData?.code=="1000"){
                    1
                } else {
                    Toast.makeText(getApplication(), "잘못된 로그인 정보입니다.", Toast.LENGTH_SHORT).show()
                    -1
                }
            }
            else -> {
                Toast.makeText(getApplication(), "로그인 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }

    fun getID(id:String){
        _id.value = id
        Log.d("idcheck", _id.value.toString())
    }

    fun getPW(pw: String){
        _pw.value = pw
    }
}