package com.smilestone.smarket.Edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.Home.HomeActivity
import com.smilestone.smarket.databinding.ActivityEditBinding
import java.text.DecimalFormat

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var model: EditViewModel
    private val priceFormat = DecimalFormat("#,###")
    private var result:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[EditViewModel::class.java]

        binding.btnExit.setOnClickListener{
            finish()
        }

        binding.btnEdit.setOnClickListener {
            model.upload()
        }

        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result == 1){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        binding.editTitle.doAfterTextChanged {
            model.liveData.value?.title = binding.editTitle.text.toString()
            Toast.makeText(this, model.liveData.value?.title.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.editPrice.doAfterTextChanged {
            if(result.equals(binding.editPrice.text.toString())){
                return@doAfterTextChanged
            }
            model.liveData.value?.price = binding.editPrice.text.toString().replace(",","").toLong()
            result = priceFormat.format(model.liveData.value?.price)
            binding.editPrice.setText(result)
            binding.editPrice.setSelection(result.length)
            Toast.makeText(this, model.liveData.value?.price.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.editContent.doAfterTextChanged {
            model.liveData.value?.content = binding.editContent.text.toString()
            Toast.makeText(this, model.liveData.value?.content.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}