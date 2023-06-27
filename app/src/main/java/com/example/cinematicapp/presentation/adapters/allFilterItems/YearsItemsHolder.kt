package com.example.cinematicapp.presentation.adapters.allFilterItems

import com.example.cinematicapp.databinding.ItemFullFiltersBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder

class YearsItemsHolder(
    private val binding: ItemFullFiltersBinding,
    private val checkedItem: (CheckedItemModel) -> Unit
) : BaseViewHolder(binding) {

    fun bind(
        item: AllFilterItemsViewModel,
        filterPosition: Int,
        checkedListItem: List<CheckedItemModel>
    ) = with(binding) {
        val yearsTitle = root.context.getString(item.item)
        filterTitle.text = yearsTitle
        val positionState = checkedListItem.find { it.itemPosition == layoutPosition && it.filterPosition == 1 }
        checkBox.isChecked = positionState != null
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            checkedItem.invoke(
                CheckedItemModel(
                    filterPosition,
                    layoutPosition,
                    isChecked
                )
            )
        }
    }
}