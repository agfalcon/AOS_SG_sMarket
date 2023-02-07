package com.smilestone.smarket.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    data class PostData(val title: String, val time: String, val price: String)

    private val list = ArrayList<PostData>()
    private val _posts = MutableLiveData<ArrayList<PostData>>()
    val posts : LiveData<ArrayList<PostData>>
        get() = _posts

    init{
        _posts.value = list
    }

    fun test(){
        list.add(PostData("김근범", "1분전", "5,000원"))
        _posts.value = list
    }
}