package com.example.cinematicapp.presentation.adapters.filterRc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemSearchFilterBinding
import com.example.cinematicapp.databinding.ItemSearchFilterBtBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.repository.data.filterRcViewListSubmit

class FilterAdapter(
    checkedItemLiveDate: MutableLiveData<Int>,
    private var visibilityLiveData: MutableLiveData<Int>,
    private val owner: LifecycleOwner,
    private var checkedListItem: List<CheckedItemModel>,
    private var callBack: (CallBack) -> Unit
    ): RecyclerView.Adapter<BaseViewHolder>() {
    private val list: List<FilterRvViewModel> by lazy { filterRcViewListSubmit() }
    init {
        checkedItemLiveDate.observe(owner) {
           notifyItemChanged(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            FilterViewTypes.FILTER_TYPE.ordinal -> FilterHolder(
                    ItemSearchFilterBinding.inflate(layoutInflater, parent, false)) {
                checkFilterCallBack(it) }
            else -> FilterButtonHolder(ItemSearchFilterBtBinding.inflate(layoutInflater, parent, false)) {
                checkButtonCallBack(it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            4 -> FilterViewTypes.BUTTON.ordinal
            else -> FilterViewTypes.FILTER_TYPE.ordinal
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder) {
            is FilterHolder -> holder.bind(list[position], checkedListItem,visibilityLiveData, owner)
            is FilterButtonHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    private fun checkFilterCallBack(filterCallBack: FilterHolder.CallBack) {
        when (filterCallBack) {
            is FilterHolder.CallBack.CheckedItem -> callBack.invoke(CallBack.CheckedItem(filterCallBack.checkItem))
            is FilterHolder.CallBack.VisibilityState -> callBack.invoke(CallBack.VisibilityState(filterCallBack.state))
        }
    }

    private fun checkButtonCallBack(filterCallBack: FilterButtonHolder.CallBack) {
        when (filterCallBack) {
            is FilterButtonHolder.CallBack.SaveListener -> callBack.invoke(CallBack.SaveListener)
            is FilterButtonHolder.CallBack.ClearListener -> callBack.invoke(CallBack.ClearListener)
        }
    }

    sealed class CallBack {
        class CheckedItem(val checkedItem: CheckedItemModel): CallBack()
        class VisibilityState(val state: Int): CallBack()
        object SaveListener : CallBack()
        object ClearListener : CallBack()
    }

    enum class FilterViewTypes {
        FILTER_TYPE,
        BUTTON
    }
}