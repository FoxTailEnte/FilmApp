package com.example.cinematicapp.presentation.adapters.homeFilm.models

import com.google.gson.annotations.SerializedName

data class BaseFilmResponse(
    @SerializedName("docs")
    val docs: List<BaseFilmInfoResponse>? = null
)

data class BaseFilmInfoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    val poster: UrlPoster? = null,
)

data class UrlPoster(
    @SerializedName("url")
    val url: String,
    @SerializedName("previewUrl")
    val previewUrl: String
)