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

    override fun getGenresLibraryFilms(page: Int, size: Int, film: Array<String>, genres: Array<String>): Single<BaseFilmResponse> = homeFilms.getGenresLibraryFilms(page, size, film, genres)
    override fun getFilmsWithFilters(page: Int,
                                     size: Int,
                                     film: Array<String>,
                                     genres: Array<String>,
                                     years: Array<String>,
                                     rating: Array<String>,
                                     country: Array<String>): Single<BaseFilmResponse> = homeFilms.getFilmsWithFilters(page, size, film, genres, years, rating, country)

    override fun getIdFilms(film: String): Single<BaseIdFilmResponse> = homeFilms.getIdFilms(film)
}