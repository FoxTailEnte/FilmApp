package com.example.cinematicapp.presentation.adapters.filterRc

import com.example.cinematicapp.databinding.ItemSearchFilterBtBinding
import com.example.cinematicapp.presentation.adapters.BaseViewHolder

class FilterButtonHolder(
    private val binding: ItemSearchFilterBtBinding,
    private val callBack: (CallBack) -> Unit
) : BaseViewHolder(binding) {

    fun bind(item: FilterRvViewModel) = with(binding) {
        btSave.setOnClickListener {
            callBack.invoke(CallBack.SaveListener)
        }
        btClear.setOnClickListener {
            callBack.invoke(CallBack.ClearListener)
        }
    }

    sealed class CallBack {
        object SaveListener : CallBack()
        object ClearListener : CallBack()
    }
}