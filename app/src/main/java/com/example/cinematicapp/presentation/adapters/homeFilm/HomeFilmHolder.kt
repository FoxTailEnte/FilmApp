package com.example.cinematicapp.presentation.adapters.homeFilm

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.squareup.picasso.Picasso

class HomeFilmHolder(
    private val binding: ItemHomeFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseFilmInfoResponse) = with(binding) {
        Picasso.get()
            .load(item.poster.previewUrl)
            .into(imageView3)
        textView5.text = item.name
    }
}