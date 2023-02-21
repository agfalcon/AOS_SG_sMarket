package com.smilestone.smarket.chat

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.data.User
import com.smilestone.smarket.dto.Chat
import com.smilestone.smarket.stomp.StompService
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.time.LocalDateTime

class ChatRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val _chatList = MutableLiveData<ArrayList<Chat>>()
    private val _message = MutableLiveData<String>()
    val url = "ws://3.34.86.115:8090/smilestone/chat/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
    private val stompClient: StompClient
    var roomNum : String


    val message : LiveData<String>
        get() = _message

    val chatList: LiveData<ArrayList<Chat>>
        get() = _chatList

    init{
        _chatList.value = ArrayList<Chat>()
        stompClient =  Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)
        roomNum = ""
    }

    fun startChat(room: String){
        StompService.runStomp(room, _chatList)
    }

    fun sendMessage(){
        if(!_message.value.isNullOrBlank()){
            StompService.sendMessage(_message.value!!)
        }
    }

    fun setMessage(message:String){
        _message.value = message
    }

    fun disconnect(){
        StompService.disconnect()
    }
}