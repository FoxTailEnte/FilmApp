package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single

interface GetHomeFilmsUseCase {

    fun getRandomFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse>

    fun getFilmsByIds(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse>

    fun getGenresFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse>

    fun getIdFilms(film: String): Single<BaseIdFilmResponse>
}