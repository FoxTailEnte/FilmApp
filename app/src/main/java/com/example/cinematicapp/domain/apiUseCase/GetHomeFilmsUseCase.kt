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

    fun getFilmsByIds(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse>

    fun getGenresFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse>

    fun getGenresLibraryFilms(page: Int, size: Int, film: Array<String>, genres: Array<String>): Single<BaseFilmResponse>

    fun getFilmsWithFilters(page: Int,
                            size: Int,
                            film: Array<String>,
                            genres: Array<String>,
                            years: Array<String>,
                            rating: Array<String>,
                            country: Array<String>): Single<BaseFilmResponse>

    fun getIdFilms(film: String): Single<BaseIdFilmResponse>
}