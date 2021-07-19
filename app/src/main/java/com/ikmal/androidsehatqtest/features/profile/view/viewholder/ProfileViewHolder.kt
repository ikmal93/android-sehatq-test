package com.ikmal.androidsehatqtest.features.profile.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.core.extension.loadImage
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity
import com.ikmal.androidsehatqtest.databinding.ItemProfileBinding

class ProfileViewHolder(
    private val itemBinding: ItemProfileBinding,
    private val onItemClick: (HistoryEntity) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(historyEntity: HistoryEntity) {
        with(itemBinding) {
            searchName.text = historyEntity.title
            searchDescription.text = historyEntity.price
            searchImage.loadImage(historyEntity.imageUrl)
            searchListConstraint.setOnClickListener {
                onItemClick.invoke(historyEntity)
            }
        }
    }
}