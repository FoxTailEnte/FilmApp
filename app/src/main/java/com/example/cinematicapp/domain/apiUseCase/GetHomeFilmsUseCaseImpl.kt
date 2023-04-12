package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import io.reactivex.Single
import javax.inject.Inject

class GetHomeFilmsUseCaseImpl @Inject constructor(
    private val homeFilms: GetHomeFilms,
) : GetHomeFilmsUseCase {

    override fun getRandomFilms(film: Array<String>): Single<BaseFilmResponse> = homeFilms.getRandomFilms(film)

    override fun getGenresFilms(film: Array<String>): Single<BaseFilmResponse> = homeFilms.getGenresFilms(film)

}