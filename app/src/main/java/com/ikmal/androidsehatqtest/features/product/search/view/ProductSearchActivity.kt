package com.ikmal.androidsehatqtest.features.product.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ikmal.androidsehatqtest.databinding.ActivityProductSearchBinding

class ProductSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setView()
        setListener()
    }

    private fun initViewBinding() {
        binding = ActivityProductSearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setView() {
        setToolbar()
    }

    private fun setToolbar() {
        with(binding) {
            setSupportActionBar(searchToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = ""
            }

            searchToolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun setListener() {

    }
}