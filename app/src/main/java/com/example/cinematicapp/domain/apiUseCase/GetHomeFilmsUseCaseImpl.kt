package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GetHomeFilmsUseCaseImpl @Inject constructor(
    private val homeFilms: GetHomeFilms,
) : GetHomeFilmsUseCase {

    override suspend fun getFilms(
        page: Int,
        size: Int,
        name: Array<String>,
        genre: Array<String>,
        years:Array<String>,
        rating: Array<String>,
        country: Array<String>,
        id: Array<String>
    ): Response<BaseFilmResponse> = homeFilms.getFilms(page, size, name, genre, years, rating, country, id)

    override fun getIdFilms(film: String): Single<BaseIdFilmResponse> = homeFilms.getIdFilms(film)
}