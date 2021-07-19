package com.ikmal.androidsehatqtest.features.home.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.core.extension.loadCircularImage
import com.ikmal.androidsehatqtest.databinding.ItemCategoryBinding
import com.ikmal.androidsehatqtest.data.api.model.Category

class CategoryViewHolder(
    private val itemBinding: ItemCategoryBinding,
    private val onItemClick: (Category) -> Unit
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(category: Category) {
        with(itemBinding) {
            ivCategoryImage.loadCircularImage(category.imageUrl)
            tvCategoryText.text = category.name
            clBaseConstraint.setOnClickListener {
                onItemClick.invoke(category)
            }
        }
    }
}