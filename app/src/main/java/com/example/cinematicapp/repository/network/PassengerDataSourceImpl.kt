package com.example.cinematicapp.repository.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.observable

class PassengerDataSourceImpl(
    private val dataSource: PassengersRepos
) : PassengerSource {

    override fun getRandomFilm(film: Array<String>, genres: Boolean) = (Pager(
        config = PagingConfig(18),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film, genres)
        }}).observable
    )

    override fun getGenresFilms(film: Array<String>, genres: Boolean) = (Pager(
        config = PagingConfig(18),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film,true)
        }}).observable
            )
}