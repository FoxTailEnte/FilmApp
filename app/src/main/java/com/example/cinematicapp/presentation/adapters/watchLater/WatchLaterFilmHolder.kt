package com.example.cinematicapp.presentation.adapters.watchLater

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class WatchLaterFilmHolder(
    private val binding: ItemHomeFilmBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseFilmInfoResponse, callBack:(BaseFilmInfoResponse) -> Unit) = with(binding) {
        if(item.poster != null) {
            Glide.with(binding.root)
                .load(item.poster.previewUrl)
                .into(ivPoster)
        }
        itemView.setOnClickListener {
            callBack.invoke(item)
        }
        tvTitle.text = item.name
    }
}