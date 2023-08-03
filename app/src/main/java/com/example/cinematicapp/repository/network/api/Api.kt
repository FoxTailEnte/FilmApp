package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    suspend fun getFilms(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("name") name: Array<String>,
        @Query("genres.name") genre: Array<String>,
        @Query("year") years: Array<String>,
        @Query("rating.kp") rating: Array<String>,
        @Query("countries.name") country: Array<String>,
        @Query("id") id: Array<String>,
    ): Response<BaseFilmResponse>

    @GET("movie/{id}")
    //@Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    @Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getIdFilms(
        @Path("id") id: String
    ): Single<BaseIdFilmResponse>
}