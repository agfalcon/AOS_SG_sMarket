package com.smilestone.smarket.Edit

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.REQUSET_ERROR
import com.smilestone.smarket.REQUSET_OK
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.STATUS_OK

class EditViewModel(application: Application) : AndroidViewModel(application) {
    data class editData(var title: String, var price: Long, var content: String)


    private val _liveData = MutableLiveData<editData>()
    val liveData: LiveData<editData>
        get() = _liveData

    init{
        _liveData.value = editData("" ,0,"")
    }

    fun upload() : Int{
        val code: Int? = ConnectService.upload(title=_liveData.value?.title?:"", content=_liveData.value?.content ?: "", price = _liveData.value?.price?: 0) ?: -1
        val result = when(code){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK ->{
                1
            }
            else -> {
                Toast.makeText(getApplication(), "업로드 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }
}