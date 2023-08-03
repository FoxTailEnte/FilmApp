package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.repository.utils.Constants

class PassengersRepos(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase
) : PagingSource<Int, BaseFilmInfoResponse>() {
    private var size = 0
    private var name = arrayOf<String>()
    private var genres = arrayOf<String>()
    private var years = arrayOf<String>()
    private var ratings = arrayOf<String>()
    private var country = arrayOf<String>()
    private var id = arrayOf<String>()

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
        id = film.get(Constants.ID) ?: arrayOf<String>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseFilmInfoResponse> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val response = getHomeFilmsUseCase.getFilms(page, pageSize, name, genres, years, ratings, country, id)
        return if(response.isSuccessful) {
            val filmList = checkNotNull(response.body()).docs
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if(response.body()?.docs!!.size < params.loadSize) null else page + 1
            LoadResult.Page(
                filmList,
                prevKey,
                nextKey
            )
        } else {
            LoadResult.Error(Exception())
        }
    }
}
