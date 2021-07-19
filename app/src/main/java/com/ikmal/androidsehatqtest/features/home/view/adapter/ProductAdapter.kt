package com.ikmal.androidsehatqtest.features.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.databinding.ItemProductBinding
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.features.home.view.viewholder.ProductViewHolder

class ProductAdapter(
    private val productPromoList: List<ProductPromo>,
    private val onItemClick: (ProductPromo) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(productPromoList[position])
    }

    override fun getItemCount(): Int {
        return productPromoList.size
    }
}