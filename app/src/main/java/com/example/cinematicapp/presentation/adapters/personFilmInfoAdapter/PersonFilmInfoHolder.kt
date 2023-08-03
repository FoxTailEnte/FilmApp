package com.example.cinematicapp.presentation.adapters.personFilmInfoAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinematicapp.databinding.ItemActorFilmInfoBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.Persons

class PersonFilmInfoHolder(
    private val binding: ItemActorFilmInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Persons) = with(binding) {
        tvName.text = item.name
        if (item.photo != null) Glide.with(binding.root)
            .load(item.photo)
            .into(ivPhoto)
    }
}