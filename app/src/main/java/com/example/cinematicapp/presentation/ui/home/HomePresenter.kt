package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val dataSource: PassengerSource
) : BasePresenter<HomeView>() {

    private val mDisposable = CompositeDisposable()
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()
    private var filterList = listOf<CheckedItemModel>()
    private var viewAttach = true
    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun saveFilters(filterItems: List<CheckedItemModel>) {
        filterList = filterItems
        if(filterItems.isNotEmpty()) {
            viewState.initRcMain(true)
        } else {
            viewState.initRcMain(false)
        }
    }

    fun getFilterItems(): List<CheckedItemModel> {
        return filterList
    }

    fun getFilmWithFilters(searchText: String?) {
       /* mDisposable.add(dataSource.getFilmsWithFilters(
            arrayOf(searchText ?: ""),
            filterList[Constants.GENRES_FILTER]?.toTypedArray() ?: arrayOf(),
            filterList[Constants.YEARS_FILTER]?.toTypedArray() ?: arrayOf(),
            filterList[Constants.RATING_FILTER]?.toTypedArray() ?: arrayOf(),
            filterList[Constants.COUNTRY_FILTER]?.toTypedArray() ?: arrayOf(),
            "Filters").subscribe {
            viewState.submitList(it)
        })*/
    }

    fun getRandomFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getRandomFilm(film, "random").subscribe {
            viewState.submitList(it)
        })
    }

    fun getFirsRandomFilms(film: Array<String>, call: String) {
        if(viewAttach) {
            currentFilmArray = film
            currentCall = call
            mDisposable.add(dataSource.getRandomFilm(film, "random").subscribe {
                viewState.submitList(it)
                viewAttach = false
            })
        }
    }

    fun getGenresFilms(film: Array<String>, call: String) {
            currentFilmArray = film
            currentCall = call
            mDisposable.add(dataSource.getGenresFilms(film,"genres").subscribe {
                viewState.submitList(it)
            })
    }

    fun getRefreshFilms() {
        when (currentCall) {
            Constants.BASE -> getRandomFilms(currentFilmArray,currentCall)
            Constants.SEARCH -> getRandomFilms(currentFilmArray,currentCall)
            Constants.GENRES -> getGenresFilms(currentFilmArray,currentCall)
        }
    }
}
