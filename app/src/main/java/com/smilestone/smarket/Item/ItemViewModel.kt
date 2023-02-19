package com.smilestone.smarket.Item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.Retrofit.ConnectService
import com.smilestone.smarket.dto.Product

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    data class itemData(var title: String, var time: String, var content: String, var view: Long, var price: Long)

    private val _item = MutableLiveData<itemData>()
    private val _product = MutableLiveData<Product>()
    private val _code = MutableLiveData<Int>()


    val code: LiveData<Int>
        get() = _code
    val item : LiveData<itemData>
        get() = _item

    val product: LiveData<Product>
        get() = _product

    init{
        _item.value = itemData("","","",0, 0)
    }

    fun item(productId: Long){
        ConnectService.item(productId, _code, _product)
    }

    fun checkCode(){
        _item.value?.title = _product.value?.title ?: ""
        _item.value?.time = _product.value?.localDateTime ?: ""
        _item.value?.content = _product.value?.content ?: ""
        _item.value?.view = _product.value?.view ?: 0
        _item.value?.price = _product.value?.price ?: 0
    }
}