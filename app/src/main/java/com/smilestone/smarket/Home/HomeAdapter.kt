package com.smilestone.smarket.Home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smilestone.smarket.R

class HomeAdapter(private val model: HomeViewModel, private val context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val userImage: ImageView = view.findViewById<ImageView>(R.id.user_image)
        val title: TextView = view.findViewById<TextView>(R.id.title)
        val time: TextView = view.findViewById<TextView>(R.id.time)
        val price: TextView = view.findViewById<TextView>(R.id.price)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text =model.posts.value?.get(position)?.title
        holder.time.text =model.posts.value?.get(position)?.time
        holder.price.text =model.posts.value?.get(position)?.price
    }

    override fun getItemCount(): Int = model.posts.value?.size ?: 0
}