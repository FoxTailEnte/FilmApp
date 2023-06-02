package com.example.cinematicapp.presentation.adapters.filterRc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemSearchFilterBinding
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.repository.data.filterRcViewListSubmit

class FilterAdapter(private var beCheckedListItem: Map<String, MutableList<String>>, private var checkedItem: (CheckedItemModel) -> Unit): RecyclerView.Adapter<FilterHolder>() {
    private val list: List<FilterRvViewModel> by lazy { filterRcViewListSubmit() }
    private var newPosition: Int? = null
    private var oldPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder = FilterHolder(
        ItemSearchFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.bind(list[position], newPosition,beCheckedListItem, {
            newPosition = it
            notifyItem()
        }) {
            checkedItem.invoke(it)
        }
    }

    private fun notifyItem() {
        notifyItemChanged(newPosition!!)
        if(oldPosition != null) notifyItemChanged(oldPosition!!)
        oldPosition = newPosition
    }

    override fun getItemCount() = list.size
}