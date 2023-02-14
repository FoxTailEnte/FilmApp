package com.example.cinematicapp.presentation.adapters.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemProfileMenuBinding
import com.example.cinematicapp.repository.data.profileListSubmit

class ProfileAdapter(
    private val callBack: (item: ProfileModel) -> Unit
) : RecyclerView.Adapter<ProfileHolder>() {

    private val list: List<ProfileModel> by lazy { profileListSubmit() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileHolder(
        ItemProfileMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        callBack.invoke(it)
    }

    override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}