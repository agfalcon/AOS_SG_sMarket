package com.smilestone.smarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smilestone.smarket.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingup.setOnClickListener {
            finish()
        }
    }
}