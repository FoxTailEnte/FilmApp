package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single

interface GetHomeFilmsUseCase {

    fun getRandomFilms(film: Array<String>): Single<BaseFilmResponse>
    fun getGenresFilms(film: Array<String>): Single<BaseFilmResponse>
}