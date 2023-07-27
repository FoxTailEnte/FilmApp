package com.example.cinematicapp.presentation.ui.bottomDialog

import androidx.lifecycle.MutableLiveData
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.repository.utils.Constants
import javax.inject.Inject

class BottomSheetDialogPresenter @Inject constructor() {
    private var mutableCheckedItemList = mutableListOf<CheckedItemModel>()
    private val checkedItemList = mutableCheckedItemList
    val checkItemLive = MutableLiveData<Int>()
    val visibilityItemLive = MutableLiveData<Int>()

    fun saveFilters(item: CheckedItemModel) {
        if (item.checked) mutableCheckedItemList.add(item)
        else removeItems(item)
        updateAdapterItem(item)
    }

    private fun removeItems(item: CheckedItemModel) {
        val element = mutableCheckedItemList.find {
            it.mainFilter == item.mainFilter && it.fullFilter == item.fullFilter }
        if (element != null) mutableCheckedItemList.remove(element)
    }

    private fun updateAdapterItem(item: CheckedItemModel) {
        when (item.mainFilter) {
            GENRES_FILTER -> checkItemLive.value = 0
            YEARS_FILTER -> checkItemLive.value = 1
            RATING_FILTER -> checkItemLive.value = 2
            COUNTRY_FILTER -> checkItemLive.value = 3
        }
    }

    fun getFilterItems(): List<CheckedItemModel> {
        return checkedItemList
    }

    fun clearList() {
        mutableCheckedItemList.clear()
    }

    fun parseCheckedList(checkedItems: List<CheckedItemModel>) {
        mutableCheckedItemList.addAll(checkedItems)
    }

    fun saveVisibilityState(state: Int) {
        visibilityItemLive.value = state
    }

    companion object {
        const val GENRES_FILTER = "Жанры"
        const val YEARS_FILTER = "Год"
        const val RATING_FILTER = "Рейтинг"
        const val COUNTRY_FILTER = "Страна"
    }
}
