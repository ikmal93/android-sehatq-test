package com.ikmal.androidsehatqtest.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ikmal.androidsehatqtest.databinding.ActivityLoginBinding
import com.ikmal.androidsehatqtest.ui.base.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setView()
        setListener()
    }

    private fun initViewBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setView() {

    }

    private fun setListener() {
        binding.mbLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}