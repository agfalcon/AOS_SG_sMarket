package com.smilestone.smarket.Edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EditViewModel(application: Application) : AndroidViewModel(application) {
    data class editData(var title: String, var price: Int, var content: String)


    private val _liveData = MutableLiveData<editData>()
    val liveData: LiveData<editData>
        get() = _liveData

    init{
        _liveData.value = editData("" ,0,"")
    }
}