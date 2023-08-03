package com.example.cinematicapp.presentation.adapters.personFilmInfoAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemActorFilmInfoBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.Persons

class PersonFilmInfoAdapter : RecyclerView.Adapter<PersonFilmInfoHolder>() {
    private var list = emptyList<Persons>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonFilmInfoHolder(
        ItemActorFilmInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PersonFilmInfoHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setСomposedData(newData: List<Persons>) {
        list = newData
        this.notifyDataSetChanged()
    }
}