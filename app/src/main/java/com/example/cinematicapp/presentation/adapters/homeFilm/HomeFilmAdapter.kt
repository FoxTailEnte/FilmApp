package com.example.cinematicapp.presentation.adapters.homeFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class HomeFilmAdapter(
    private val callBack: (item: BaseFilmInfoResponse) -> Unit,
) : PagingDataAdapter<BaseFilmInfoResponse , HomeFilmHolder>(HomeFilmDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeFilmHolder(
        ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        callBack.invoke(it)
    }

    override fun onBindViewHolder(holder: HomeFilmHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}