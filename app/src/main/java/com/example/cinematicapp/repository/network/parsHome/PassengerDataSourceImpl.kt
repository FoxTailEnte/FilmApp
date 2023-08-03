package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class PassengerDataSourceImpl(
    private val dataSource: PassengersRepos
) : PassengerSource {
    override fun getRandomFilm(film: MutableMap<String, Array<String>>): Observable<PagingData<BaseFilmInfoResponse>> {
        return try {
            Pager(
                config = PagingConfig(12),
                pagingSourceFactory = { dataSource.apply { submitFilmList(film, 100) } }
            ).observable.cachedIn(CoroutineScope(Dispatchers.Main))
        } catch (e: Exception) {
            Observable.error(e)
        }
    }
}