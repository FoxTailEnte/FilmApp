package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.Response

interface GetHomeFilmsUseCase {

    suspend fun getFilms(page: Int,
                 size: Int,
                 name: Array<String> = arrayOf<String>(),
                 genre: Array<String> = arrayOf<String>(),
                 years:Array<String> = arrayOf<String>(),
                 rating: Array<String> = arrayOf<String>(),
                 country: Array<String> = arrayOf<String>(),
                 id: Array<String> = arrayOf<String>()
    ): Response<BaseFilmResponse>

    fun getIdFilms(film: String): Single<BaseIdFilmResponse>
}