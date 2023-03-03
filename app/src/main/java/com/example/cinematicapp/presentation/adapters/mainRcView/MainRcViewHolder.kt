package com.example.cinematicapp.presentation.adapters.mainRcView

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemRcViewMainBinding

class MainRcViewHolder(
    private val binding: ItemRcViewMainBinding,
    private val callBack: (item: MainRcViewModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainRcViewModel) = with(binding) {
        textView13.text = root.context.getString(item.name)
        textView13.setOnClickListener {
            callBack.invoke(item)
        }
        }
    }