package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single

interface GetHomeFilms {

    fun getAllFilms(film: Array<String>): Single<BaseFilmResponse>
}