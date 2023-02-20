package com.smilestone.smarket.item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.data.User
import com.smilestone.smarket.databinding.ActivityItemBinding
import java.text.DecimalFormat

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    private lateinit var model: ItemViewModel
    private val priceFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getLongExtra(PRODUCT_ID, 0)

        model = ViewModelProvider(this)[ItemViewModel::class.java]


        model.code.observe(this, Observer{
            model.product.observe(this, Observer{
                model.checkCode()
                binding.title.text = model.item.value?.title
                binding.time.text = model.item.value?.time
                binding.view.text = model.item.value?.view.toString()
                binding.content.text = model.item.value?.content
                binding.textPrice.text = priceFormat.format(model.item.value?.price) + "원"

                if(model.product.value?.sellerId == User.id){
                    binding.btnChat.visibility = View.INVISIBLE
                }
            })
        })



        model.item(productId)

    }
}