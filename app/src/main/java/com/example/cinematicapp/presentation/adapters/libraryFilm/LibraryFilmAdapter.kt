package com.example.cinematicapp.presentation.adapters.libraryFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class LibraryFilmAdapter(
    private val callBack: (item: BaseFilmInfoResponse) -> Unit,
) : PagingDataAdapter<BaseFilmInfoResponse ,LibraryFilmHolder>(LibraryFilmDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LibraryFilmHolder(
        ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: LibraryFilmHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}