package com.smilestone.smarket.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smilestone.smarket.R
import com.smilestone.smarket.databinding.ActivityChatRoomBinding

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}