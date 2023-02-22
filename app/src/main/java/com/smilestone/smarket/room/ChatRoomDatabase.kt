package com.smilestone.smarket.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ChatRoom::class], version = 1)
abstract class ChatRoomDatabase: RoomDatabase() {
    abstract fun getChatRoomDao(): ChatRoomDao
}