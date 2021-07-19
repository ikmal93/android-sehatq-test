package com.ikmal.androidsehatqtest.features.product.search.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.core.extension.loadImage
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import com.ikmal.androidsehatqtest.databinding.ItemProfileBinding

class ProductSearchViewHolder(
    private val itemBinding: ItemProfileBinding,
    private val onItemClick: (ProductPromo) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(productPromo: ProductPromo) {
        with(itemBinding) {
            searchName.text = productPromo.title
            searchDescription.text = productPromo.price
            searchImage.loadImage(productPromo.imageUrl)
            searchListConstraint.setOnClickListener {
                onItemClick.invoke(productPromo)
            }
        }
    }
}