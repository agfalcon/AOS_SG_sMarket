package com.smilestone.smarket.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChatRoomDao {

    @Insert
    fun insert(chatRoom: ChatRoom)

    @Update
    fun update(chatRoom: ChatRoom)

    @Delete
    fun delete(chatRoom: ChatRoom)

    @Query("SELECT * FROM ChatRoom")
    fun getChatRoomList() : ArrayList<ChatRoom>
}