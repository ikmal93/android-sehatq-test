package com.ikmal.androidsehatqtest.features.product.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.databinding.ItemProfileBinding
import com.ikmal.androidsehatqtest.features.product.search.view.viewholder.ProductSearchViewHolder

class ProductSearchAdapter(
    private val products: List<ProductPromo>,
    private val onItemClick: (ProductPromo) -> Unit
) : RecyclerView.Adapter<ProductSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSearchViewHolder {
        val itemBinding =
            ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductSearchViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ProductSearchViewHolder, position: Int) {
        holder.bindItem(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}