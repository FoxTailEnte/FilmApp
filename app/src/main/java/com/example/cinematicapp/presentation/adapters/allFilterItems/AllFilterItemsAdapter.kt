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
    private var mainFilter: String,
    private val checkedItem: (CheckedItemModel) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {
    private val list = mutableListOf<AllFilterItemsViewModel>()

    override fun getItemViewType(position: Int): Int =
        when (mainFilter) {
            GENRES_FILTER -> FilterViewTypes.GENRES_TYPE.ordinal
            YEARS_FILTER -> FilterViewTypes.YEARS_TYPE.ordinal
            RATING_FILTER -> FilterViewTypes.RATING_TYPE.ordinal
            COUNTRY_FILTER -> FilterViewTypes.COUNTRY_TYPE.ordinal
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
            is GenresItemsHolder -> holder.bind(list[position], mainFilter, checkedListItem)
            is YearsItemsHolder -> holder.bind(list[position], mainFilter, checkedListItem)
            is RatingItemsHolder -> holder.bind(list[position], mainFilter, checkedListItem)
            is CountryItemsHolder -> holder.bind(list[position], mainFilter, checkedListItem)
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

    companion object {
        const val GENRES_FILTER = "Жанры"
        const val YEARS_FILTER = "Год"
        const val RATING_FILTER = "Рейтинг"
        const val COUNTRY_FILTER = "Страна"
    }
}