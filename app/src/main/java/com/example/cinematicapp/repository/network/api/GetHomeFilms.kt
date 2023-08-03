package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.Response

interface GetHomeFilms {

    suspend fun getFilms(
        page: Int,
        size: Int,
        name: Array<String>,
        genre: Array<String>,
        years:Array<String>,
        rating: Array<String>,
        country: Array<String>,
        id: Array<String>
    ): Response<BaseFilmResponse>

    fun getIdFilms(film: String): Single<BaseIdFilmResponse>
}