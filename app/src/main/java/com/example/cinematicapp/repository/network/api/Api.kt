package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    fun getRandomFilms(
        @Query("name") name: Array<String>
    ): Single<BaseFilmResponse>

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    fun getGenresFilms(
        @Query("genres.name") name: Array<String>
    ): Single<BaseFilmResponse>
}