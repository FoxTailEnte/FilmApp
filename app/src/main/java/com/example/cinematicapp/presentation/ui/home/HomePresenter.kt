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
    private val checkedItem = mutableListOf<String>()

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun saveFilters(item: CheckedItemModel) {
        if(item.checked) checkedItem.add(item.item.toString())
        else checkedItem.remove(item.item.toString())
    }

    fun getFilterItems() = checkedItem

    fun getRandomFilms(film: Array<String>, call: String) {
            currentFilmArray = film
            currentCall = call
            mDisposable.add(dataSource.getRandomFilm(film,"random").subscribe {
                viewState.submitList(it)
            })
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
