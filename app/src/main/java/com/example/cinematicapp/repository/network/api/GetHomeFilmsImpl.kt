package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
) : GetHomeFilms {


    override fun getAllFilms(film: Array<String>): Single<BaseFilmResponse> = api.getRandomFilm(film)


}