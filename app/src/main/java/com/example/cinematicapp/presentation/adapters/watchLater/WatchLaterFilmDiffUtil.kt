package com.example.cinematicapp.presentation.adapters.watchLater

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class WatchLaterFilmDiffUtil : DiffUtil.ItemCallback<BaseFilmInfoResponse>() {
    override fun areItemsTheSame(oldItem: BaseFilmInfoResponse, newItem: BaseFilmInfoResponse): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseFilmInfoResponse, newItem: BaseFilmInfoResponse): Boolean {
        return oldItem === newItem
    }

}