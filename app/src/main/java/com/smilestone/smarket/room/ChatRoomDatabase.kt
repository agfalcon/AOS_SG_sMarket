package com.smilestone.smarket.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ChatRoom::class], version = 1)
abstract class ChatRoomDatabase: RoomDatabase() {
    abstract fun getChatRoomDao(): ChatRoomDao

    companion object {
        private var instance: ChatRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ChatRoomDatabase? {
            if (instance == null)
                synchronized(ChatRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChatRoomDatabase::class.java,
                        "chatDB"
                    )
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}