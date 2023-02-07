package com.smilestone.smarket.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.Chat.ChatActivity
import com.smilestone.smarket.Edit.EditActivity
import com.smilestone.smarket.Search.SearchActivity
import com.smilestone.smarket.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var model: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[HomeViewModel::class.java]
        homeAdapter = HomeAdapter(model, this)

        model.posts.observe(this){
            homeAdapter.notifyDataSetChanged()
        }

        binding.postList.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = homeAdapter
        }

        for(i in  0..10){
            model.test()
        }
        binding.btnSearch.setOnClickListener(this)
        binding.btnEdit.setOnClickListener (this)
        binding.btnChat.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        val intent = when(v?.id){
            binding.btnSearch.id -> Intent(this, SearchActivity::class.java)
            binding.btnChat.id -> Intent(this, ChatActivity::class.java)
            binding.btnEdit.id -> Intent(this, EditActivity::class.java)
            else -> return
        }
        startActivity(intent)
    }
}