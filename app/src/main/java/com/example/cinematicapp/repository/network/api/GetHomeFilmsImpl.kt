package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
) : GetHomeFilms {


    override fun getRandomFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse> = api.getRandomFilms(page, size, film)

    override fun getGenresFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse> = api.getGenresFilms(page, size, film)

    override fun getIdFilms(film: String): Single<BaseIdFilmResponse> = api.getIdFilms(film)
}