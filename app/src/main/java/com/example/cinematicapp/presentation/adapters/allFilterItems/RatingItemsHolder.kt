package com.example.cinematicapp.presentation.adapters.allFilterItems

import com.example.cinematicapp.databinding.ItemFullFiltersBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder
import com.example.cinematicapp.repository.utils.Constants

class RatingItemsHolder(
    private val binding: ItemFullFiltersBinding,
    private val checkedItem: (CheckedItemModel) -> Unit
) : BaseViewHolder(binding) {

    fun bind(
        item: AllFilterItemsViewModel,
        mainFilter: String,
        checkedListItem: List<CheckedItemModel>
    ) = with(binding) {
        val ratingTitle = root.context.getString(item.item)
        filterTitle.text = ratingTitle
        val positionState = checkedListItem.find { it.fullFilter == ratingTitle && it.mainFilter == Constants.RATING_FILTER }
        checkBox.isChecked = positionState != null
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            checkedItem.invoke(
                CheckedItemModel(
                    mainFilter,
                    ratingTitle,
                    isChecked
                )
            )
        }
    }
}