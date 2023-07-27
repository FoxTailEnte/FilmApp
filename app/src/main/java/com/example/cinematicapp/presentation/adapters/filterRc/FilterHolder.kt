package com.example.cinematicapp.presentation.adapters.filterRc

import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ItemSearchFilterBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder
import com.example.cinematicapp.presentation.adapters.allFilterItems.AllFilterItemsAdapter
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel

class FilterHolder(
    private val binding: ItemSearchFilterBinding,
    private val callBack: (CallBack) -> Unit
) : BaseViewHolder(binding) {

    fun bind(
        item: FilterRvViewModel,
        checkedListItem: List<CheckedItemModel>,
        visibilityItemLive: MutableLiveData<Int>,
        owner: LifecycleOwner
    ) = with(binding) {
        val title = root.context.getString(item.name)
        filterTitle.text = title
        val positionState = checkedListItem.find { it.mainFilter == title }
        visibilityItemLive.observe(owner) {
            rcFullFilters.isVisible = layoutPosition == it
            viewFull.isVisible = layoutPosition == it
        }
        if (positionState != null) filterTitle.setTextColor(root.context.resources.getColor(R.color.white))
        else filterTitle.setTextColor(root.context.resources.getColor(R.color.main_text))
        itemView.setOnClickListener {
            callBack.invoke(CallBack.VisibilityState(layoutPosition))
        }
        val allItemsAdapter =
            AllFilterItemsAdapter(checkedListItem, title) {
                callBack.invoke(CallBack.CheckedItem(it))
            }
        rcFullFilters.adapter = allItemsAdapter
        allItemsAdapter.submitList(layoutPosition)
    }

    sealed class CallBack {
        class CheckedItem(val checkItem: CheckedItemModel) : CallBack()
        class VisibilityState(val state: Int) : CallBack()
    }
}