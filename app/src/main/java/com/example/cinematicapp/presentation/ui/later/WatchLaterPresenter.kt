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
    private val firestore: FireBaseDataUseCase,
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
        firestore.getWatchLater(getUserPhone()) {
            val list = it?.toTypedArray()
            if (list != null) getRandomFilms(list, "")
        }
    }

    private fun getRandomFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getFilmsById(film, "id", film.size).subscribe {
            viewState.submitList(it)
        })
    }


     fun getRefreshFilms() {
         when (currentCall) {
             Constants.BASE -> getRandomFilms(currentFilmArray,currentCall)
             //Constants.SEARCH -> getRandomFilms(currentFilmArray,currentCall)
             //Constants.GENRES -> getGenresFilms(currentFilmArray,currentCall)
         }
     }
}