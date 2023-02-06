package com.smilestone.smarket.SignUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var model: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this)[SignUpViewModel::class.java]


        binding.editId.doAfterTextChanged {
            model.signUpData.value?.id = binding.editId.text.toString()
        }
        binding.editPw.doAfterTextChanged {
            model.signUpData.value?.pw = binding.editPw.text.toString()
        }
        binding.editEmail.doAfterTextChanged {
            model.signUpData.value?.email = binding.editEmail.text.toString()
        }
        binding.editNickname.doAfterTextChanged {
            model.signUpData.value?.nickname = binding.editNickname.text.toString()
        }
        binding.btnSingup.setOnClickListener {
            val result = model.signUp()
            if(result==1){
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}