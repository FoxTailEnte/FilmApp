package com.example.cinematicapp.presentation.adapters.mainRcView

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ItemRcViewMainBinding

class MainRcViewHolder(
    private val binding: ItemRcViewMainBinding,
    private val callBack: (item: MainRcViewModel) -> Unit,
    private val selectPosition: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainRcViewModel, newPos: Int, oldPos: Int) = with(binding) {
        when (layoutPosition) {
            newPos -> tvGenre.setTextColor(binding.root.context.resources.getColor(R.color.white))
            oldPos -> tvGenre.setTextColor(binding.root.context.resources.getColor(R.color.main_text))
            else -> tvGenre.setTextColor(binding.root.context.resources.getColor(R.color.main_text))
        }
        tvGenre.text = root.context.getString(item.name)
        tvGenre.setOnClickListener {
            selectPosition.invoke(layoutPosition)
            callBack.invoke(item)
        }
    }
}