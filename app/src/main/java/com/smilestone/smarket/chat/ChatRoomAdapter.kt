package com.smilestone.smarket.chat

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.R
import com.smilestone.smarket.data.User
import com.smilestone.smarket.home.HomeAdapter
import com.smilestone.smarket.home.HomeViewModel
import com.smilestone.smarket.item.ItemActivity

class ChatRoomAdapter(private val model: ChatRoomViewModel, private val context: Context): RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //val inout: TextView = view.findViewById<TextView>(R.id.message_inout)
        val linear: LinearLayout = view.findViewById<LinearLayout>(R.id.linear)
        val nickname: TextView = view.findViewById<TextView>(R.id.message_nickname)
        val background: ImageView = view.findViewById<ImageView>(R.id.message_background)
        val message: TextView = view.findViewById<TextView>(R.id.message_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = model.chatList.value?.get(position)
        if(!message?.sender.equals(User.nickname)){
            holder.nickname.text = message?.sender
            holder.background.setImageResource(R.drawable.partner_chat)
        }
        else{
            holder.nickname.visibility = View.GONE
            holder.background.setImageResource(R.drawable.my_chat)
            holder.linear.gravity = Gravity.END
        }
        holder.message.text = message?.message

    }

    override fun getItemCount(): Int = model.chatList.value?.size ?: 0
}