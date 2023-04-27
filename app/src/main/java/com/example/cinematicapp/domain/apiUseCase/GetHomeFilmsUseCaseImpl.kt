package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsUseCaseImpl @Inject constructor(
    private val homeFilms: GetHomeFilms,
) : GetHomeFilmsUseCase {

    override fun getRandomFilms(page: Int, size: Int, film: Array<String>,): Single<BaseFilmResponse> = homeFilms.getRandomFilms(page, size, film)

    override fun getFilmsByIds(page: Int, size: Int,film: Array<String>,): Single<BaseFilmResponse> = homeFilms.getFilmsByIds(page, size, film)

    override fun getGenresFilms(page: Int, size: Int, film: Array<String>): Single<BaseFilmResponse> = homeFilms.getGenresFilms(page, size, film)

    override fun getIdFilms(film: String): Single<BaseIdFilmResponse> = homeFilms.getIdFilms(film)
}