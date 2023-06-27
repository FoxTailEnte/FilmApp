package com.example.cinematicapp.presentation.ui.bottomDialog

import androidx.lifecycle.MutableLiveData
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import javax.inject.Inject

class BottomSheetDialogPresenter @Inject constructor() {
    private var mutableCheckedItemList = mutableListOf<CheckedItemModel>()
    private val checkedItemList = mutableCheckedItemList
    val checkItemLive = MutableLiveData<Int>()
    val visibilityItemLive = MutableLiveData<Int>()

    fun saveFilters(item: CheckedItemModel) {
        if (item.checked) {
            mutableCheckedItemList.add(item)
        } else {
            val element = mutableCheckedItemList.find {
                it.filterPosition == item.filterPosition && it.itemPosition == item.itemPosition
            }
            if (element != null) mutableCheckedItemList.remove(element)
        }
        checkItemLive.value = item.filterPosition
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
}
