package com.example.cinematicapp.presentation.adapters.homeFilm

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cinematicapp.databinding.ItemHomeFilmBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse

class HomeFilmHolder(
    private val binding: ItemHomeFilmBinding,
    private val callBack: (item: BaseFilmInfoResponse) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseFilmInfoResponse) = with(binding) {
        if(item.poster != null) {
            Glide.with(binding.root)
                .load(item.poster.previewUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(imageView3)
        }
        tvTitle.text = item.name
        itemView.setOnClickListener {
            callBack.invoke(item)
        }
    }
}