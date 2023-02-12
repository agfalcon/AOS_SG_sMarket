package com.smilestone.smarket.Item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.Retrofit.ConnectService

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    data class itemData(var title: String, var time: String, var content: String, var view: Long)

    private val _item = MutableLiveData<itemData>()
    val item : LiveData<itemData>
        get() = _item

    init{
        _item.value = itemData("","","",0)
    }

    fun item(){
        ConnectService.item(7)
        _item.value?.title = ConnectService.itemData?.title ?: ""
        _item.value?.time = ConnectService.itemData?.localDateTime ?: ""
        _item.value?.content = ConnectService.itemData?.content ?: ""
        _item.value?.view = ConnectService.itemData?.view ?: 0
    }
}