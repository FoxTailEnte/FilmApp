package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
) : GetHomeFilms {


    override fun getRandomFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse> = api.getRandomFilms(page, size, film)

    override fun getFilmsByIds(page: Int, size: Int,film: Array<String>): Single<BaseFilmResponse> = api.getFilmsByIds(page, size,film)

    override fun getGenresFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse> = api.getGenresFilms(page, size, film)

    override fun getGenresLibraryFilms(page: Int, size: Int, film: Array<String>, genres: Array<String>): Single<BaseFilmResponse> = api.getGenresLibraryFilms(page, size, film, genres)
    override fun getFilmsWithFilters(page: Int,
                                     size: Int,
                                     film: Array<String>,
                                     genres: Array<String>,
                                     years: Array<String>,
                                     rating: Array<String>,
                                     country: Array<String>): Single<BaseFilmResponse> = api.getFilmsWithFilters(page, size, film, years, rating, genres, country)

    override fun getIdFilms(film: String): Single<BaseIdFilmResponse> = api.getIdFilms(film)
}