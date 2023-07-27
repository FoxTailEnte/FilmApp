package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
) : GetHomeFilms {


    override fun getFilms(
        page: Int,
        size: Int,
        name: Array<String>,
        genre: Array<String>,
        years:Array<String>,
        rating: Array<String>,
        country: Array<String>,
        id: Array<String>
    ): Single<Response<BaseFilmResponse>> = api.getFilms(page, size, name, genre, years, rating, country, id)

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