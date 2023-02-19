package com.smilestone.smarket.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.Chat.ChatActivity
import com.smilestone.smarket.Edit.EditActivity
import com.smilestone.smarket.Item.ItemActivity
import com.smilestone.smarket.Search.SearchActivity
import com.smilestone.smarket.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var model: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    companion object{
        var keyword : String = ""
        var isSearch = false
    }
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
        binding.btnSearch.setOnClickListener(this)
        binding.btnEdit.setOnClickListener (this)
        binding.btnChat.setOnClickListener (this)
        binding.btnInfo.setOnClickListener(this)



        model.code.observe(this, Observer {
            model.post.observe(this, Observer {
                model.checkCode()
            })
        })


    }

    override fun onResume() {
        super.onResume()
        if(isSearch){
            isSearch =false
            model.search(keyword)
            keyword = ""
        }
        else{
            model.homeService()
        }
    }

    override fun onClick(v: View?) {
        Log.d("intent", v.toString())
        val intent = when(v?.id){
            binding.btnSearch.id -> Intent(applicationContext, SearchActivity::class.java)
            binding.btnChat.id -> Intent(applicationContext, ChatActivity::class.java)
            binding.btnEdit.id -> Intent(applicationContext, EditActivity::class.java)
            binding.btnInfo.id -> Intent(applicationContext, ItemActivity::class.java)
            else -> return
        }
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        Log.d("intent", intent.toString())
        startActivity(intent)
    }
}