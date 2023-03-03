package com.example.cinematicapp.presentation.adapters.homeFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemHomeFilmBinding

class HomeFilmAdapter(
) : RecyclerView.Adapter<HomeFilmHolder>() {

    private val list: MutableList<ResponseModel2> by lazy { mutableListOf() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeFilmHolder(
        ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeFilmHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(list: ResponseModel) {
        this.list.addAll(list.docs.map{
            it
        })
        notifyDataSetChanged()
    }
}