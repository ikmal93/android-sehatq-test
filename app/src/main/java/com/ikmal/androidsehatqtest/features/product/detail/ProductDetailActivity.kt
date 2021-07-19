package com.ikmal.androidsehatqtest.features.product.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikmal.androidsehatqtest.R
import com.ikmal.androidsehatqtest.core.constant.IntentKey
import com.ikmal.androidsehatqtest.core.database.DatabaseBuilder
import com.ikmal.androidsehatqtest.core.extension.loadImage
import com.ikmal.androidsehatqtest.core.network.ApiBuilder
import com.ikmal.androidsehatqtest.core.utils.Status
import com.ikmal.androidsehatqtest.core.viewmodel.ViewModelFactory
import com.ikmal.androidsehatqtest.databinding.ActivityProductDetailBinding
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity
import com.ikmal.androidsehatqtest.features.profile.view.adapter.ProfileAdapter
import com.ikmal.androidsehatqtest.features.profile.viewmodel.ProfileViewModel

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var messageToShare: String = ""
    private lateinit var viewModel: ProfileViewModel
    private var productPromo: ProductPromo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setupViewModel()
        setView()
        setupObservers()
        setListener()
    }

    private fun initViewBinding() {
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiBuilder.apiService, DatabaseBuilder.getInstance(this)
            )
        ).get(ProfileViewModel::class.java)
    }

    private fun setView() {
        productPromo = intent.getSerializableExtra(IntentKey.PRODUCT) as? ProductPromo
        messageToShare = productPromo?.title ?: ""
        setToolbar(productPromo?.title ?: "")
        with(binding) {
            productImage.loadImage(productPromo?.imageUrl ?: "")
            productDescription.text = productPromo?.description ?: ""
            productPrice.text = productPromo?.price ?: ""
        }
    }

    private fun setToolbar(titleToolbar: String) {
        with(binding) {
            setSupportActionBar(productToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = titleToolbar
            }

            productToolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun setupObservers() {
        viewModel.postHistories().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun setListener() {
        binding.apply {
            productBuyBtn.setOnClickListener {
                viewModel.insertHistories(
                    HistoryEntity(
                        id = productPromo?.id ?: "",
                        description = productPromo?.description ?: "",
                        imageUrl = productPromo?.imageUrl ?: "",
                        loved = productPromo?.loved ?: 0,
                        price = productPromo?.price ?: "",
                        title = productPromo?.title ?: ""
                    )
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.product_detail_share) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, messageToShare)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return super.onOptionsItemSelected(item)

    }


}