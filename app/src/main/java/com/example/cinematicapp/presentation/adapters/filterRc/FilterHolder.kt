package com.example.cinematicapp.presentation.adapters.filterRc

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ItemSearchFilterBinding
import com.example.cinematicapp.presentation.adapters.allFilterItems.AllFilterItemsAdapter
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel

class FilterHolder(
    private val binding: ItemSearchFilterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FilterRvViewModel,
             newPosition: Int?,
             beCheckedListItem: Map<String, MutableList<String>>,
             positionListener: (Int) -> Unit,
             checkedItem: (CheckedItemModel) -> Unit) = with(binding) {
        val title = root.context.getString(item.name)
        filterTitle.text = title
        if(!beCheckedListItem[title].isNullOrEmpty()) filterTitle.setTextColor(root.context.resources.getColor(R.color.white))
        rcFullFilters.isVisible = layoutPosition == newPosition
        viewFull.isVisible = layoutPosition == newPosition
        viewMainFilter.isVisible = layoutPosition != newPosition
        itemView.setOnClickListener {
            positionListener.invoke(layoutPosition)
        }
        val allItemsAdapter = AllFilterItemsAdapter(beCheckedListItem,root.context.getString(item.name)) {
            checkedItem.invoke(it)
        }
        rcFullFilters.adapter = allItemsAdapter
    }
}