package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single

interface GetHomeFilms {

    fun getRandomFilms(film: Array<String>): Single<BaseFilmResponse>

    fun getGenresFilms(film: Array<String>): Single<BaseFilmResponse>

    fun getIdFilms(film: String): Single<BaseIdFilmResponse>
}