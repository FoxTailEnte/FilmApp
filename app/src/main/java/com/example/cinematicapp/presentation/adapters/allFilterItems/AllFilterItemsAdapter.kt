package com.example.cinematicapp.presentation.adapters.allFilterItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemFullFiltersBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder
import com.example.cinematicapp.repository.data.allFilterCountryListSubmit
import com.example.cinematicapp.repository.data.allFilterGenresListSubmit
import com.example.cinematicapp.repository.data.allFilterRatingListSubmit
import com.example.cinematicapp.repository.data.allFilterYearsListSubmit

class AllFilterItemsAdapter(
    private var checkedListItem: List<CheckedItemModel>,
    private var filterPosition: Int,
    private val checkedItem: (CheckedItemModel) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {
    private val list = mutableListOf<AllFilterItemsViewModel>()

    override fun getItemViewType(position: Int): Int =
        when (filterPosition) {
            0 -> FilterViewTypes.GENRES_TYPE.ordinal
            1 -> FilterViewTypes.YEARS_TYPE.ordinal
            2 -> FilterViewTypes.RATING_TYPE.ordinal
            else -> FilterViewTypes.COUNTRY_TYPE.ordinal
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FilterViewTypes.GENRES_TYPE.ordinal -> GenresItemsHolder(
                ItemFullFiltersBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            ) {
                checkedItem.invoke(it)
            }

            FilterViewTypes.YEARS_TYPE.ordinal -> YearsItemsHolder(
                ItemFullFiltersBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            ) {
                checkedItem.invoke(it)
            }

            FilterViewTypes.RATING_TYPE.ordinal -> RatingItemsHolder(
                ItemFullFiltersBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            ) {
                checkedItem.invoke(it)
            }

            else -> CountryItemsHolder(
                ItemFullFiltersBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            ) {
                checkedItem.invoke(it)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is GenresItemsHolder -> holder.bind(list[position], filterPosition, checkedListItem)
            is YearsItemsHolder -> holder.bind(list[position], filterPosition, checkedListItem)
            is RatingItemsHolder -> holder.bind(list[position], filterPosition, checkedListItem)
            is CountryItemsHolder -> holder.bind(list[position], filterPosition, checkedListItem)
        }
    }

    override fun getItemCount() = list.size

    fun submitList(position: Int) {
        when (position) {
            0 -> list.addAll(allFilterGenresListSubmit())
            1 -> list.addAll(allFilterYearsListSubmit())
            2 -> list.addAll(allFilterRatingListSubmit())
            3 -> list.addAll(allFilterCountryListSubmit())
        }
    }

    enum class FilterViewTypes {
        GENRES_TYPE,
        YEARS_TYPE,
        RATING_TYPE,
        COUNTRY_TYPE
    }
}