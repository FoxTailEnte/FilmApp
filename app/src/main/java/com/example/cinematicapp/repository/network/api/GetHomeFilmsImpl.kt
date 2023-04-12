package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
) : GetHomeFilms {


    override fun getRandomFilms(film: Array<String>): Single<BaseFilmResponse> = api.getRandomFilms(film)

    override fun getGenresFilms(film: Array<String>): Single<BaseFilmResponse> = api.getGenresFilms(film)


}