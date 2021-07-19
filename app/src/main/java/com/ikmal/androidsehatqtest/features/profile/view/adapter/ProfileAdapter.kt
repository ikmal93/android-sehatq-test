package com.ikmal.androidsehatqtest.features.profile.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikmal.androidsehatqtest.data.local.model.entity.history.HistoryEntity
import com.ikmal.androidsehatqtest.databinding.ItemProfileBinding
import com.ikmal.androidsehatqtest.features.profile.view.viewholder.ProfileViewHolder

class ProfileAdapter(
    private val histories: List<HistoryEntity>,
    private val onItemClick: (HistoryEntity) -> Unit
) : RecyclerView.Adapter<ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemBinding =
            ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(histories[position])
    }

    override fun getItemCount(): Int {
        return histories.size
    }
}