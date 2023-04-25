package com.example.cinematicapp.presentation.ui.labrary

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.network.parsLibrary.PassengerLibrarySource
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LibraryPresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val dataSource: PassengerLibrarySource
) : BasePresenter<LibraryView>() {

    private val mDisposable = CompositeDisposable()
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()

    fun getRandomFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getRandomFilm(film,false).subscribe {
            viewState.submitList(it)
        })
    }

    fun getGenresFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getGenresFilms(film,true).subscribe {
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