package com.example.cinematicapp.presentation.adapters.homeFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemHomeFilmBinding

class HomeFilmAdapter(
) : RecyclerView.Adapter<HomeFilmHolder>() {

    private var list = emptyList<BaseFilmInfoResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeFilmHolder(
        ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeFilmHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setСomposedData(newData: List<BaseFilmInfoResponse>) {
        val diffUtil = HomeFilmDiffUtil(list, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newData
        diffResult.dispatchUpdatesTo(this)


    }


}