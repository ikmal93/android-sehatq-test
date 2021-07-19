package com.ikmal.androidsehatqtest.features.product

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ikmal.androidsehatqtest.R
import com.ikmal.androidsehatqtest.core.constant.IntentKey
import com.ikmal.androidsehatqtest.core.extension.loadImage
import com.ikmal.androidsehatqtest.databinding.ActivityProductDetailBinding
import com.ikmal.androidsehatqtest.features.home.data.api.data.ProductPromo

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var messageToShare: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setView()
        setListener()
    }

    private fun initViewBinding() {
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setView() {
        val product = intent.getSerializableExtra(IntentKey.PRODUCT) as? ProductPromo
        messageToShare = product?.title ?: ""
        setToolbar(product?.title ?: "")
        with(binding) {
            productImage.loadImage(product?.imageUrl ?: "")
            productDescription.text = product?.description ?: ""
            productPrice.text = product?.price ?: ""
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

    private fun setListener() {

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