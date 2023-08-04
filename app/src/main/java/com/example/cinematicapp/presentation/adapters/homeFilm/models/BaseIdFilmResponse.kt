package com.example.cinematicapp.presentation.adapters.homeFilm.models

data class BaseIdFilmResponse(
    val backdrop: BigPoster,
    val rating: Rating,
    val movieLength: String? = null,
    val spokenLanguages: List<Language>,
    val name: String,
    val slogan: String? = null,
    val budget: Budget,
    val description: String,
    val year: Int,
    val poster: Poster,
    val videos: YouTube? = null,
    val genres: List<Genres>,
    val persons: List<Persons>,
    val countries: List<Countries>,
    val ageRating: Int
)

data class BigPoster(
    val url: String? = null,
    val previewUrl: String? = null
)

data class Budget(
    val value: String? = null,
    val currency: String? = null
)

data class Rating(
    val kp: Double
)

data class Poster(
    val url: String,
    val previewUrl: String
)

data class Language(
    val nameEn: String
)

data class YouTube(
    val trailers: List<Trailer>? = null
)

data class Trailer(
    val url: String,
    val name: String,
    val site: String,
)

data class Genres(
    val name: String
)

data class Persons(
    val photo: String? = null,
    val name: String? = null,
    val profession: String
)

data class Countries(
    val name: String
)

