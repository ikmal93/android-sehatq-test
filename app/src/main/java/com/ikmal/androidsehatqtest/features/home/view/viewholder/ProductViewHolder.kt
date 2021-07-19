package com.ikmal.androidsehatqtest.features.home.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.core.extension.loadImage
import com.ikmal.androidsehatqtest.databinding.ItemProductBinding
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo

class ProductViewHolder(
    private val itemBinding: ItemProductBinding,
    private val onItemClick: (ProductPromo) -> Unit
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(product: ProductPromo) {
        with(itemBinding) {
            productImageList.loadImage(product.imageUrl)
            productName.text = product.title
            productContentList.text = product.description
            productRootCard.setOnClickListener {
                onItemClick.invoke(product)
            }
        }
    }
}