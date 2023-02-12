package com.smilestone.smarket.Home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.REQUSET_ERROR
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.STATUS_OK

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    data class PostData(val id: Long, val title: String, val time: String, val price: String)

    private val list = ArrayList<PostData>()
    private val _posts = MutableLiveData<ArrayList<PostData>>()
    val posts : LiveData<ArrayList<PostData>>
        get() = _posts

    init{
        _posts.value = list
    }

    fun test(){
        list.add(PostData(0, "김근범", "1분전", "5,000원"))
        _posts.value = list
    }

    fun homeService(){
        val code: Int? = ConnectService.home() ?: -1
        when(code){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
            }
            STATUS_OK ->{
                list.clear()
                for(i in ConnectService.productData!!){
                    list.add(PostData(i.productId, i.title, i.localDateTime, i.price.toString()))
                }
                _posts.value = list
            }
            else -> {
                Toast.makeText(getApplication(), "게시글 불러오기 오류", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun update(){
        list.clear()
        for(i in ConnectService.productData!!){
            list.add(PostData(i.productId, i.title, i.localDateTime, i.price.toString()))
        }
        _posts.value = list
    }
}