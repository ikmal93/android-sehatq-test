package com.ikmal.androidsehatqtest.features.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.databinding.ItemCategoryBinding
import com.ikmal.androidsehatqtest.data.api.model.Category
import com.ikmal.androidsehatqtest.features.home.view.viewholder.CategoryViewHolder

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val onItemClick: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItem(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}