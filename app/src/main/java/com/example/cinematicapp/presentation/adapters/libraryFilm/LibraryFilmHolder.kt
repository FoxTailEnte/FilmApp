package com.example.cinematicapp.presentation.adapters.libraryFilm

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class LibraryFilmHolder(
    private val binding: ItemHomeFilmBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseFilmInfoResponse) = with(binding) {
        Log.d("MyLog", "123123")
        if(item.poster != null) {
            Glide.with(binding.root)
                .load(item.poster.previewUrl)
                .into(imageView3)
        }
        tvTitle.text = item.name
    }
}