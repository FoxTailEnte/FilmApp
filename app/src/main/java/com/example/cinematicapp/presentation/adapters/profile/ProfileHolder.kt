package com.example.cinematicapp.presentation.adapters.profile

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemProfileMenuBinding

class ProfileHolder(
    private val binding: ItemProfileMenuBinding,
    private val callBack: (item: ProfileModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProfileModel) = with(binding) {
        tvTitle.text = root.context.getString(item.name)
        imItem.setImageResource(item.icon)
        root.setOnClickListener {
            callBack.invoke(item)
        }
    }
}