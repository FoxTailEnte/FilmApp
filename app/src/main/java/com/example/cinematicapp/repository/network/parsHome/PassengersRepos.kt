package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PassengersRepos(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase
) : RxPagingSource<Int, BaseFilmInfoResponse>() {

    private val filmList by lazy { mutableListOf<String>() }
    private var isGenres = false

    override fun getRefreshKey(state: PagingState<Int, BaseFilmInfoResponse>): Int? {
        return null
    }

    fun submitFilmList(film: Array<String>?, genres: Boolean) {
        isGenres = genres
        filmList.clear()
        filmList.addAll(film!!.asList())
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BaseFilmInfoResponse>> {
        val nextPageNumber = params.key ?: 1
        if(isGenres) {
            return getHomeFilmsUseCase.getGenresFilms(nextPageNumber, 18, film = filmList.toTypedArray())
                .subscribeOn(Schedulers.io())
                .map {
                    toResponseResult(nextPageNumber, it)
                }
                .onErrorReturn {
                    LoadResult.Error(it)
                }
        } else {
            return getHomeFilmsUseCase.getRandomFilms(nextPageNumber, 18, film = filmList.toTypedArray())
                .subscribeOn(Schedulers.io())
                .map {
                    toResponseResult(nextPageNumber, it)
                }
                .onErrorReturn {
                    LoadResult.Error(it)
                }
        }
    }

    private fun toResponseResult(position:Int, response: BaseFilmResponse): LoadResult<Int, BaseFilmInfoResponse> = LoadResult.Page(
            data = response.docs!!,
        prevKey = if (position == 1) null else position - 1,
        nextKey = if (position == 100) null else position + 1
        )
}
