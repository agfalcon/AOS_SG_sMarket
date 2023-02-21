package com.smilestone.smarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    val url = "ws://3.34.86.115:8090/smilestone/chat/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
    val stompClient =  Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runStomp()

    }

    fun runStomp(){

        stompClient.topic("/chat/${"0"}").subscribe { topicMessage ->
            Log.d("날아오는것", topicMessage.payload)
        }

        stompClient.connect()

        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.i("테스트", "open")
                }
                LifecycleEvent.Type.CLOSED -> {
                    Log.i("테스트", "닫김")

                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("테스트", "에러")
                    Log.d("테스트", lifecycleEvent.exception.toString())
                }
                else ->{
                    Log.d("테스트", lifecycleEvent.message)
                }
            }
        }

        val data = JSONObject()
        data.put("roomId", "0")
        data.put("sender", "tester")
        data.put("message", "테스트")
        data.put("chatAt", LocalDateTime.now().toString())

        stompClient.send("/pub/chat.${"0"}", data.toString()).subscribe()
    }
}