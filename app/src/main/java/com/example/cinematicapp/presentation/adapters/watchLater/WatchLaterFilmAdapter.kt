package com.example.cinematicapp.presentation.adapters.watchLater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class WatchLaterFilmAdapter(
    private val callBack: (item: BaseFilmInfoResponse) -> Unit,
) : PagingDataAdapter<BaseFilmInfoResponse ,WatchLaterFilmHolder>(WatchLaterFilmDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WatchLaterFilmHolder(
        ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WatchLaterFilmHolder, position: Int) {
        holder.bind(getItem(position)!!) {
            callBack.invoke(it)
        }
    }
}