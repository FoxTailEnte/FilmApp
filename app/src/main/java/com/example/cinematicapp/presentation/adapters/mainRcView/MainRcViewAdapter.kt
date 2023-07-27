package com.example.cinematicapp.presentation.adapters.mainRcView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemRcViewMainBinding
import com.example.cinematicapp.repository.data.mainRcViewListSubmit

class MainRcViewAdapter(
    private val colorState: Boolean,
    private val callBack: (item: MainRcViewModel) -> Unit
) : RecyclerView.Adapter<MainRcViewHolder>() {
    private val list: MutableList<MainRcViewModel> = mutableListOf()
    private var newPosition: Int = 0
    private var oldPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRcViewHolder(
        ItemRcViewMainBinding.inflate(LayoutInflater.from(parent.context), parent, false),{
            callBack.invoke(it)
        }) {
        newPosition = it
        updateSelectItem(it)
    }

    override fun onBindViewHolder(holder: MainRcViewHolder, position: Int) {

        holder.bind(list[position], newPosition, colorState)
    }

    private fun updateSelectItem(pos: Int) {
       notifyItemChanged(pos)
       notifyItemChanged(oldPosition)
        oldPosition = pos
    }

    override fun getItemCount() = list.size

    fun submitList() {
         list.addAll(mainRcViewListSubmit())
    }
}