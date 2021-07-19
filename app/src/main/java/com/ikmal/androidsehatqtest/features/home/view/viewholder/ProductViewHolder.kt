package com.ikmal.androidsehatqtest.features.home.view.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.R
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
            if (product.loved > 0) {
                productBtnFavList.setImageDrawable(
                    ContextCompat.getDrawable(
                        root.context,
                        R.drawable.ic_favorite_red
                    )
                )
            } else {
                productBtnFavList.setImageDrawable(
                    ContextCompat.getDrawable(
                        root.context,
                        R.drawable.ic_favorite
                    )
                )
            }
            productRootCard.setOnClickListener {
                onItemClick.invoke(product)
            }
        }
    }
}