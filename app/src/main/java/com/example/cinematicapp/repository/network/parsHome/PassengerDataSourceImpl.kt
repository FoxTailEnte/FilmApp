package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class PassengerDataSourceImpl(
    private val dataSource: PassengersRepos
) : PassengerSource {
    override fun getRandomFilm(film: Array<String>, type: String) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film, null, type, 100)
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))

    override fun getGenresFilms(film: Array<String>, type: String) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film, null, type,100)
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))

    override fun getFilmsById(film: Array<String>, type: String, listSize: Int) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film, null,type, listSize)
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))

    override fun getGenresLibraryFilms(film: Array<String>, genres: Array<String>, type: String, listSize: Int) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film, genres, type, listSize)
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))

    override fun getFilmsWithFilters(name: Array<String>,
                                     genres: Array<String>,
                                     years: Array<String>,
                                     rating: Array<String>,
                                     country: Array<String>,
                                     type: String) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(name, genres, years, rating, country, type, 100 )
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))
}