package com.example.cinematicapp.presentation.adapters.homeFilm

import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.squareup.picasso.Picasso

class HomeFilmHolder(
    private val binding: ItemHomeFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResponseModel2) = with(binding) {
        if (item.poster != null) {
            Picasso.get()
                .load(item.poster.previewUrl)
                .resize(500, 500).centerInside()
                .into(imageView3)
            textView5.text = item.name
        }
    }
}