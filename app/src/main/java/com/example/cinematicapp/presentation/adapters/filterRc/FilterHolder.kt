package com.example.cinematicapp.presentation.adapters.filterRc

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemSearchFilterBinding
import com.example.cinematicapp.presentation.adapters.allFilterItems.AllFilterItemsAdapter
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel

class FilterHolder(
    private val binding: ItemSearchFilterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FilterRvViewModel, newPosition: Int?, listItem:List<String>, positionListener: (Int) -> Unit, checkedItem: (CheckedItemModel) -> Unit) = with(binding) {
        filterTitle.text = root.context.getString(item.name)
        rcFullFilters.isVisible = layoutPosition == newPosition
        viewFull.isVisible = layoutPosition == newPosition
        viewMainFilter.isVisible = layoutPosition != newPosition
            itemView.setOnClickListener {
            positionListener.invoke(layoutPosition)
        }
        val allItemsAdapter = AllFilterItemsAdapter(listItem,layoutPosition) {
            checkedItem.invoke(it)
        }
        rcFullFilters.adapter = allItemsAdapter
    }
}