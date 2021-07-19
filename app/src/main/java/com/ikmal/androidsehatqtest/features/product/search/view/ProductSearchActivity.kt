package com.ikmal.androidsehatqtest.features.product.search.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ikmal.androidsehatqtest.core.constant.IntentKey
import com.ikmal.androidsehatqtest.core.database.DatabaseBuilder
import com.ikmal.androidsehatqtest.core.network.ApiBuilder
import com.ikmal.androidsehatqtest.core.utils.Status
import com.ikmal.androidsehatqtest.core.viewmodel.ViewModelFactory
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.databinding.ActivityProductSearchBinding
import com.ikmal.androidsehatqtest.features.home.viewmodel.HomeViewModel
import com.ikmal.androidsehatqtest.features.product.detail.ProductDetailActivity
import com.ikmal.androidsehatqtest.features.product.search.view.adapter.ProductSearchAdapter
import com.ikmal.androidsehatqtest.features.product.search.viewmodel.ProductSearchViewModel
import com.ikmal.androidsehatqtest.features.profile.view.adapter.ProfileAdapter

class ProductSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductSearchBinding
    private lateinit var viewModel: ProductSearchViewModel
    private lateinit var productSearchAdapter: ProductSearchAdapter

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

        binding.apply {
            searchRecycler.setHasFixedSize(true)
            searchRecycler.layoutManager =
                LinearLayoutManager(this@ProductSearchActivity)
        }

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
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setFilterKeyword(s.toString())
            }
        })
    }

    private fun setupObserver() {
        viewModel.getPromoProducts().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        })

        viewModel.getFilterProducts().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { productList ->
                        binding.apply {
                            productSearchAdapter = ProductSearchAdapter(productList.map {
                                ProductPromo(
                                    id = it.id,
                                    description = it.description,
                                    imageUrl = it.imageUrl,
                                    loved = it.loved,
                                    price = it.price,
                                    title = it.title
                                )
                            }) {
                                startActivity(
                                    Intent(
                                        this@ProductSearchActivity,
                                        ProductDetailActivity::class.java
                                    )
                                        .putExtra(
                                            IntentKey.PRODUCT, ProductPromo(
                                                id = it.id,
                                                description = it.description,
                                                imageUrl = it.imageUrl,
                                                loved = it.loved,
                                                price = it.price,
                                                title = it.title
                                            )
                                        )
                                )
                            }
                            searchRecycler.adapter = productSearchAdapter
                        }
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        })
    }

//    Log.d("KAKA", "HASIL : ${Gson().toJson(it.data)}")
//                    it.data?.let { productList ->
//                        binding.apply {
//                            searchRecycler.setHasFixedSize(true)
//                            searchRecycler.layoutManager =
//                                LinearLayoutManager(this@ProductSearchActivity)
//                            productSearchAdapter = ProductSearchAdapter(productList.map {
//                                ProductPromo(
//                                    id = it.id,
//                                    description = it.description,
//                                    imageUrl = it.imageUrl,
//                                    loved = it.loved,
//                                    price = it.price,
//                                    title = it.title
//                                )
//                            }) {
//                                startActivity(
//                                    Intent(
//                                        this@ProductSearchActivity,
//                                        ProductDetailActivity::class.java
//                                    )
//                                        .putExtra(
//                                            IntentKey.PRODUCT, ProductPromo(
//                                                id = it.id,
//                                                description = it.description,
//                                                imageUrl = it.imageUrl,
//                                                loved = it.loved,
//                                                price = it.price,
//                                                title = it.title
//                                            )
//                                        )
//                                )
//                            }
//                            searchRecycler.adapter = productSearchAdapter
//                        }
//                    }
}