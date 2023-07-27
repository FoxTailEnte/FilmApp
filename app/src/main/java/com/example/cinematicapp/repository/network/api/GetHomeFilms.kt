package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.Response

interface GetHomeFilms {

    fun getFilms(
        page: Int,
        size: Int,
        name: Array<String>,
        genre: Array<String>,
        years:Array<String>,
        rating: Array<String>,
        country: Array<String>,
        id: Array<String>
    ): Single<Response<BaseFilmResponse>>

    fun getFilmsByIds(page: Int, size: Int,film: Array<String>): Single<BaseFilmResponse>

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