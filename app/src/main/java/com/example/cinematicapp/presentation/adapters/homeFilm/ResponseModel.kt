package com.example.cinematicapp.presentation.adapters.homeFilm

data class BaseFilmResponse (
    val docs: List<BaseFilmInfoResponse>
    )
data class BaseFilmInfoResponse (
    val name: String,
    val poster: Poster
        )
data class Poster (
    val previewUrl: String
        )
