package com.smilestone.smarket.Home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.REQUSET_ERROR
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.dto.Product
import kotlin.concurrent.thread

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    data class PostData(val id: Long, val title: String, val time: String, val price: String)

    private val list = ArrayList<PostData>()
    private val _posts = MutableLiveData<ArrayList<PostData>>()
    private val _code = MutableLiveData<Int>()
    private val _post = MutableLiveData<ArrayList<Product>>()

    val post : LiveData<ArrayList<Product>>
        get() = _post

    val posts : LiveData<ArrayList<PostData>>
        get() = _posts

    val code : LiveData<Int>
        get() = _code

    init{
        _posts.value = list
    }

    fun test(){
        list.add(PostData(0, "김근범", "1분전", "5,000원"))
        _posts.value = list
    }

    fun homeService(){
        ConnectService.home(_code, _post)
    }

    fun checkCode(){
        when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
            }
            STATUS_OK ->{
                list.clear()
                for(i in 0 until post.value?.size!!){
                    val data = post.value?.get(i)
                    list.add(PostData(data?.productId ?: 0, data?.title ?: "", data?.localDateTime ?: "", data?.price.toString() ))
                }
                _posts.value = list
            }
            else -> {
                Toast.makeText(getApplication(), "게시글 불러오기 오류", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun search(keyword: String){
        ConnectService.search(keyword, _code, _post)
    }
}