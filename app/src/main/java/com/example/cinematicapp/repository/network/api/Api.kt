package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @GET("movie")
    @Headers("X-API-KEY: ZZ81QVH-9604YYP-Q462SHP-Y74PR68")
   suspend fun getRandomFilm(
        @Query("name") name: Array<String>
    ): ResponseModel
}