package com.example.cinematicapp.presentation.adapters.allFilterItems

import com.example.cinematicapp.databinding.ItemFullFiltersBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder

class CountryItemsHolder(
    private val binding: ItemFullFiltersBinding,
    private val checkedItem: (CheckedItemModel) -> Unit
) : BaseViewHolder(binding) {

    fun bind(
        item: AllFilterItemsViewModel,
        filterPosition: Int,
        checkedListItem: List<CheckedItemModel>
    ) = with(binding) {
        val countryTitle = root.context.getString(item.item)
        filterTitle.text = countryTitle
        val positionState = checkedListItem.find { it.itemPosition == layoutPosition && it.filterPosition == 3 }
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