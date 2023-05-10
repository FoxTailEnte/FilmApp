package com.example.cinematicapp.presentation.adapters.allFilterItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemFullFiltersBinding
import com.example.cinematicapp.repository.data.allFilterItemsViewListSubmit

class AllFilterItemsAdapter(
    var item: List<String>,
    var poz: Int,
    private var checkedItem: (CheckedItemModel) -> Unit
    ): RecyclerView.Adapter<AllFilterItemsHolder>() {
    private val list: List<AllFilterItemsViewModel> by lazy { allFilterItemsViewListSubmit() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFilterItemsHolder = AllFilterItemsHolder(
        ItemFullFiltersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AllFilterItemsHolder, position: Int) {
        holder.bind(list[position],poz,item) {
            checkedItem.invoke(it)
        }
    }

    override fun getItemCount() = list.size
}