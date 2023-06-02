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

    private val filmList by lazy { mutableListOf<String>() }
    private val filmGenresList by lazy { mutableListOf<String>() }
    private var size = 0
    private var searchType = ""

    private val namear by lazy { mutableListOf<String>() }
    private val genrear by lazy { mutableListOf<String>() }
    private val yearsar by lazy { mutableListOf<String>() }
    private val ratingar by lazy { mutableListOf<String>() }
    private val countryar by lazy { mutableListOf<String>() }

    override fun getRefreshKey(state: PagingState<Int, BaseFilmInfoResponse>): Int? {
        return null
    }

    fun submitFilmList(film: Array<String>?, genres: Array<String>?, type: String, listSize: Int) {
        size = listSize
        searchType = type
        filmGenresList.clear()
        if(genres != null)filmGenresList.addAll(genres.asList())
        filmList.clear()
        filmList.addAll(film!!.asList())
    }

    fun submitFilmList(film: Array<String>?, genres: Array<String>?, years: Array<String>?, rating: Array<String>?, country: Array<String>?, type: String, listSize: Int) {
        size = listSize
        searchType = type
        namear.clear()
        genrear.clear()
        yearsar.clear()
        ratingar.clear()
        countryar.clear()

        namear.addAll(film!!.asList())
        genrear.addAll(genres!!.asList())
        yearsar.addAll(years!!.asList())
        ratingar.addAll(rating!!.asList())
        countryar.addAll(country!!.asList())
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BaseFilmInfoResponse>> {
        val nextPageNumber = params.key ?: 1
        when (searchType) {
            Constants.GENRES -> {
                return getHomeFilmsUseCase.getGenresFilms(nextPageNumber, 12, film = filmList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .map {
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        LoadResult.Error(it)
                    }
            }
            Constants.ID -> {
                return getHomeFilmsUseCase.getFilmsByIds(nextPageNumber, 12, film = filmList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .map {
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        LoadResult.Error(it)
                    }
            }
            Constants.SEARCH -> {
                return getHomeFilmsUseCase.getFilmsByIds(nextPageNumber, 12, film = filmList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .map {
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        LoadResult.Error(it)
                    }
            }
            Constants.GENRES_LIBRARY -> {
                return getHomeFilmsUseCase.getGenresLibraryFilms(nextPageNumber, 12, film = filmList.toTypedArray(), filmGenresList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .map {
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        LoadResult.Error(it)
                    }
            }
            Constants.FILTER -> {
                return getHomeFilmsUseCase.getFilmsWithFilters(
                    nextPageNumber,
                    12,
                    film = namear.toTypedArray(),
                    genres = genrear.toTypedArray(),
                    years = yearsar.toTypedArray(),
                    rating = ratingar.toTypedArray(),
                    country = countryar.toTypedArray()
                )
                    .subscribeOn(Schedulers.io())
                    .map {
                        Log.d("MyLog", it.docs.toString())
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        Log.d("MyLog", it.toString())
                        LoadResult.Error(it)
                    }
            }
            else -> {
                return getHomeFilmsUseCase.getRandomFilms(nextPageNumber, 12, film = filmList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .map {
                        toResponseResult(nextPageNumber, it)
                    }
                    .onErrorReturn {
                        LoadResult.Error(it)
                    }
            }
        }
    }

    private fun toResponseResult(position:Int, response: BaseFilmResponse): LoadResult<Int, BaseFilmInfoResponse> = LoadResult.Page(
            data = response.docs!!,
        prevKey = if (position == 1) null else position - 1,
        nextKey = if (position == size) null else position + 1
        )
}
