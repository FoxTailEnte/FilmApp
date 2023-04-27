package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class WatchLaterPresenter @Inject constructor(
    private val dataSource: PassengerSource,
    private val fireStore: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<WatchLaterView>() {

    private val mDisposable = CompositeDisposable()
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()
    private lateinit var phone: String

    private fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getWatchLaterList() {
        fireStore.getWatchLater(getUserPhone()) {
            val list = it?.toTypedArray()
            if (list != null) getRandomWatchLaterFilms(list, Constants.ID)
        }
    }

    fun getRandomWatchLaterFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getFilmsById(film, currentCall, film.size).subscribe {
            viewState.submitList(it)
        })
    }

    fun getGenresWatchLaterFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getFilmsById(film, currentCall, film.size).subscribe {
            viewState.submitList(it)
        })
    }


     fun getRefreshFilms() {
         when (currentCall) {
             Constants.BASE -> getRandomWatchLaterFilms(currentFilmArray,currentCall)
             //Constants.SEARCH -> getRandomFilms(currentFilmArray,currentCall)
             Constants.GENRES -> getGenresWatchLaterFilms(currentFilmArray,currentCall)
         }
     }
}