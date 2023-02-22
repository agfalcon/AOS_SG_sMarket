package com.smilestone.smarket.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatRoom(
    @PrimaryKey
    var roomId: Long,
    var title: String,
    var lastChat: String,
    var lastChatAt: String
)
