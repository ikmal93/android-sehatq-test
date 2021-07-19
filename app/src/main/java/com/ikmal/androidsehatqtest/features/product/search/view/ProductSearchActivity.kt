package com.ikmal.androidsehatqtest.features.product.search.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ikmal.androidsehatqtest.core.database.DatabaseBuilder
import com.ikmal.androidsehatqtest.core.network.ApiBuilder
import com.ikmal.androidsehatqtest.core.utils.Status
import com.ikmal.androidsehatqtest.core.viewmodel.ViewModelFactory
import com.ikmal.androidsehatqtest.databinding.ActivityProductSearchBinding
import com.ikmal.androidsehatqtest.features.home.viewmodel.HomeViewModel
import com.ikmal.androidsehatqtest.features.product.search.viewmodel.ProductSearchViewModel

class ProductSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductSearchBinding
    private lateinit var viewModel: ProductSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setupViewModel()
        setView()
        setListener()
        setupObserver()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiBuilder.apiService, DatabaseBuilder.getInstance(this))
        ).get(ProductSearchViewModel::class.java)
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

    private fun setupObserver() {
//        viewModel.getAll().observe(this, {
//            when (it.status) {
//                Status.LOADING -> {
//                }
//                Status.SUCCESS -> {
//                    it.data?.let { users ->
//
//                    }
//                }
//                Status.ERROR -> {
//                }
//            }
//        })
    }
}