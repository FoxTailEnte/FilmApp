package com.example.cinematicapp.presentation.adapters.allFilterItems

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemFullFiltersBinding

class AllFilterItemsHolder(
    private val binding: ItemFullFiltersBinding,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: AllFilterItemsViewModel, poz: Int,listItem: List<String>, checkedItem: (CheckedItemModel) -> Unit) = with(binding) {
        when (poz) {
            0 -> {
                filterTitle.text = root.context.getString(item.genre)
                if(listItem.contains(item.genre.toString())) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,item.genre))
                }
            }
            1 -> {
                filterTitle.text = root.context.getString(item.years)
                if(listItem.contains(item.years.toString())) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,item.years))
                }
            }
            2 -> {
                if (item.rating == null) {
                    itemView.isVisible = false
                    itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                } else {
                    filterTitle.text = root.context.getString(item.rating!!)
                    if(listItem.contains(item.rating.toString())) checkBox.isChecked = true
                    checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                       checkedItem.invoke(CheckedItemModel(isChecked,item.rating!!))
                    }
                }
            }
            3 -> {
                if (item.country == null) itemView.isVisible = false
                else filterTitle.text = root.context.getString(item.country!!)
                if(listItem.contains(item.country.toString())) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,item.country!!))
                }
            }
        }

    }
}