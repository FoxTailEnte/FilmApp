package com.example.cinematicapp.presentation.adapters.main

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ItemRcViewMainBinding

class MainRcViewHolder(
    private val binding: ItemRcViewMainBinding,
    private val callBack: (item: MainRcViewModel) -> Unit,
    private val selectPosition: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainRcViewModel, itemPosition: Int, state: Boolean) = with(binding) {
            if(itemPosition == layoutPosition && state) {
                tvGenre.setTextColor(binding.root.context.resources.getColor(R.color.white))
            } else {
                tvGenre.setTextColor(binding.root.context.resources.getColor(R.color.main_text))
            }
        tvGenre.text = root.context.getString(item.name)
        tvGenre.setOnClickListener {
            selectPosition.invoke(layoutPosition)
            callBack.invoke(item)
        }
    }
}