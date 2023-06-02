package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getRandomFilms(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("name") name: Array<String>
    ): Single<BaseFilmResponse>

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getFilmsByIds(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("id") name: Array<String>
    ): Single<BaseFilmResponse>

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getGenresFilms(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("genres.name") genre: Array<String>
    ): Single<BaseFilmResponse>

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getGenresLibraryFilms(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("id") name: Array<String>,
        @Query("genres.name") genre: Array<String>
    ): Single<BaseFilmResponse>

    @GET("movie/{id}")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getIdFilms(
        @Path("id") id: String
    ): Single<BaseIdFilmResponse>

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
    //@Headers("X-API-KEY: YE3YWPE-9K7M3VQ-HT5TP07-SKBYY2X")
    //@Headers("X-API-KEY: 0NFEGQ1-NBX4WWZ-NMZMKBY-6TDXH6Q")
    fun getFilmsWithFilters(
        @Query("page") page: Int,
        @Query("limit") size: Int = 10,
        @Query("name") name: Array<String>,
        @Query("year") years: Array<String>,
        @Query("rating.kp") rating: Array<String>,
        @Query("genres.name") genre: Array<String>,
        @Query("countries.name") country: Array<String>
    ): Single<BaseFilmResponse>
}