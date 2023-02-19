package com.smilestone.smarket.SignUp

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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    data class userData(var id: String, var pw: String, var nickname: String)

    private val _signUpData = MutableLiveData<userData>()
    private val _code = MutableLiveData<Int>()

    val signUpData: LiveData<userData>
        get() = _signUpData

    val code : LiveData<Int>
        get() = _code

    init{
        _signUpData.value = userData("","","")
    }

    fun signUp(){
        if(!checkData())
            return
        ConnectService.signUp(_signUpData.value?.id.toString(),_signUpData.value?.pw.toString(), _signUpData.value?.nickname.toString(), _code)
        //Log.d("확인용", result.toString())

    }

    fun checkCode(): Int{
        val result = when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            REQUSET_OK ->1
            REQUSET_ERROR->{
                Toast.makeText(getApplication(), ConnectService.signUpData?.message.toString(), Toast.LENGTH_SHORT).show()
                -1
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
             _signUpData.value?.nickname?.isEmpty() == true ){
            Toast.makeText(getApplication(), "회원가입 양식을 모두 채워주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}