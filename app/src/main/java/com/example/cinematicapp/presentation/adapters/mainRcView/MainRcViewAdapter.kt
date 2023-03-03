package com.example.cinematicapp.presentation.adapters.mainRcView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemRcViewMainBinding
import com.example.cinematicapp.repository.data.mainRcViewListSubmit

class MainRcViewAdapter(
    private val callBack: (item: MainRcViewModel) -> Unit
) : RecyclerView.Adapter<MainRcViewHolder>() {

    private val list: List<MainRcViewModel> by lazy { mainRcViewListSubmit() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRcViewHolder(
        ItemRcViewMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        callBack.invoke(it)
    }

    override fun onBindViewHolder(holder: MainRcViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}