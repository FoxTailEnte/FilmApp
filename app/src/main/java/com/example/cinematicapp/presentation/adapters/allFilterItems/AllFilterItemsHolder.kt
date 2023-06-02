package com.example.cinematicapp.presentation.adapters.allFilterItems

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemFullFiltersBinding

class AllFilterItemsHolder(
    private val binding: ItemFullFiltersBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AllFilterItemsViewModel,
             poz: String,
             beCheckedListItem: Map<String, MutableList<String>>,
             checkedItem: (CheckedItemModel) -> Unit) = with(binding) {
        when (poz) {
            GENRES -> {
                val genreTitle = root.context.getString(item.genre)
                filterTitle.text = genreTitle
                val containsList = beCheckedListItem[GENRES]
                checkItemContains(containsList, genreTitle)
                if(checkItemContains(containsList,genreTitle)) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,root.context.getString(item.genre).lowercase(),poz))
                }
            }
            YEARS -> {
                val yearsTitle = root.context.getString(item.years)
                filterTitle.text = yearsTitle
                val containsList = beCheckedListItem[YEARS]
                checkItemContains(containsList, yearsTitle)
                if(checkItemContains(containsList, yearsTitle)) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,root.context.getString(item.years),poz))
                }
            }
            RATING -> {
                if (item.rating == null) {
                    itemView.isVisible = false
                    itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                } else {
                    val ratingTitle = root.context.getString(item.rating!!)
                    filterTitle.text = ratingTitle
                    val containsList = beCheckedListItem[RATING]
                    checkItemContains(containsList, ratingTitle)
                    if(checkItemContains(containsList, ratingTitle)) checkBox.isChecked = true
                    checkBox.setOnCheckedChangeListener { _, isChecked ->
                       checkedItem.invoke(CheckedItemModel(isChecked,root.context.getString(item.rating!!),poz))
                    }
                }
            }
            COUNTRY -> {
                val countryTitle = root.context.getString(item.country!!)
                if (item.country == null) itemView.isVisible = false
                else filterTitle.text = countryTitle
                val containsList = beCheckedListItem[COUNTRY]
                if(checkItemContains(containsList, countryTitle)) checkBox.isChecked = true
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    checkedItem.invoke(CheckedItemModel(isChecked,root.context.getString(item.country!!),poz))
                }
            }
        }
    }

    private fun checkItemContains(list: MutableList<String>?, name: String): Boolean {
        return list?.toString()?.contains(name.lowercase()) ?: false
    }

    companion object {
        const val GENRES = "Жанры"
        const val YEARS = "Год"
        const val RATING = "Рейтинг"
        const val COUNTRY = "Страна"
    }
}