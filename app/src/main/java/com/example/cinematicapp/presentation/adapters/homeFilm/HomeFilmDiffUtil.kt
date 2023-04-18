package com.example.cinematicapp.presentation.adapters.homeFilm

import androidx.recyclerview.widget.DiffUtil
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class HomeFilmDiffUtil(
    private val oldList: List<BaseFilmInfoResponse>,
    private val newList: List<BaseFilmInfoResponse>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].poster != newList[newItemPosition].poster -> false
            else -> true
        }
    }
}