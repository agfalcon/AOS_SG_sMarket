package com.smilestone.smarket.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatRoom(
    @PrimaryKey
    val roomId: Long,
    val title: String,
    val lastChat: String,
    val lastChatAt: String
)
