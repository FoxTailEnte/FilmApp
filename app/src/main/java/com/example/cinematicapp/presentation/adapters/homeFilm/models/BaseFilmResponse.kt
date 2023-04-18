package com.example.cinematicapp.presentation.adapters.homeFilm.models

data class BaseFilmResponse(
    val docs: List<BaseFilmInfoResponse>? = null
)

data class BaseFilmInfoResponse(
    val id: Int,
    val name: String,
    val poster: UrlPoster? = null,
)

data class UrlPoster(
    val url: String,
    val previewUrl: String
)