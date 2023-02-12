package com.smilestone.smarket.Item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    private lateinit var model: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[ItemViewModel::class.java]

        model.item.observe(this, Observer{
            binding.title.text = model.item.value?.title
            binding.time.text = model.item.value?.time
            binding.view.text = model.item.value?.view.toString()
            binding.content.text = model.item.value?.content
        })

        model.item()

    }
}