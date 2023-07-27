package com.example.cinematicapp.repository.network.parsHome

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PassengersRepos(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase
) : RxPagingSource<Int, BaseFilmInfoResponse>() {
    private var size = 0
    private var name = arrayOf<String>()
    private var genres = arrayOf<String>()
    private var years = arrayOf<String>()
    private var ratings = arrayOf<String>()
    private var country = arrayOf<String>()

    override fun getRefreshKey(state: PagingState<Int, BaseFilmInfoResponse>): Int? {
        return null
    }

    fun submitFilmList(film: MutableMap<String, Array<String>>, listSize: Int) {
        size = listSize
        name = film.get(Constants.SEARCH) ?: arrayOf<String>()
        genres = film.get(Constants.GENRES_FILTER) ?: arrayOf<String>()
        years = film.get(Constants.YEARS_FILTER) ?: arrayOf<String>()
        ratings = film.get(Constants.RATING_FILTER) ?: arrayOf<String>()
        country = film.get(Constants.COUNTRY_FILTER) ?: arrayOf<String>()
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BaseFilmInfoResponse>> {
        val nextPageNumber = params.key ?: 1
        return getHomeFilmsUseCase.getFilms(nextPageNumber, 12, name, genres, years, ratings, country)
            .subscribeOn(Schedulers.io())
            .map {
                if (it.code() != 200) {
                    Log.d("MyLog", "Error number is - " + it.code().toString())
                } else {
                    Log.d("MyLog", "code " + it.toString())
                }
                toResponseResult(nextPageNumber, it.body(), it.code() == 200)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toResponseResult(position: Int, response: BaseFilmResponse?, isSuccessful: Boolean): LoadResult<Int, BaseFilmInfoResponse> {
        val data = response?.docs
        val nextPageKey = if (isSuccessful && position < size) position + 1 else null
        return LoadResult.Page(
            data = data ?: emptyList(),
            prevKey = if (position == 1) null else position - 1,
            nextKey = nextPageKey
        )
    }
}
