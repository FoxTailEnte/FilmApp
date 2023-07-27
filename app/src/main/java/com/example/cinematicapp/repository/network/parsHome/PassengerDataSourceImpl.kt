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
    override fun getRandomFilm(film: MutableMap<String, Array<String>>) = (Pager(
        config = PagingConfig(12),
        pagingSourceFactory = {dataSource.apply {
            submitFilmList(film,  100)
        }}).observable.cachedIn(CoroutineScope(Dispatchers.Main)))
}