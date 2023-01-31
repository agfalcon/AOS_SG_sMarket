package com.smilestone.smarket.Login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.Retrofit.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val LOGIN_SERVER_URL = ""
    }

    private val _id = MutableLiveData<String>()
    private val _pw = MutableLiveData<String>()

    val id : LiveData<String>
        get() = _id
    val pw : LiveData<String>
        get() = _pw

    fun login(){
        val code: Int? = ConnectService.login(_id.value.toString(), _pw.value.toString())
    }
}