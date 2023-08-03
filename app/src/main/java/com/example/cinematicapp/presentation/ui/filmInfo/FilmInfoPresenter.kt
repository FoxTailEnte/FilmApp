package com.example.cinematicapp.presentation.ui.filmInfo

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FilmInfoPresenter @Inject constructor(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase,
    private val firebase: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<FilmInfoView>() {

    private lateinit var phone: String
    private var libraryList = hashMapOf<String, Int>()
    private var watchLaterList = hashMapOf<String, Int>()
    private var title = ""


    fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getIdFilms(film: String?) {
        if (film != null) {
            getHomeFilmsUseCase.getIdFilms(film).singleRequest { response ->
                title = response.name
                viewState.setFilmInfo(response)
                viewState.submitList(response.persons)
            }
        }
    }

    fun checkLibraryItem(filmId: Int) {
        firebase.checkLibraryItem(phone, filmId) { filmList ->
            if (filmList == null) {
                libraryList[title] = filmId
                checkFilmState(false, Type.LIBRARY, filmId)
            } else {
                libraryList = filmList
                checkFilmState(
                    filmList.values.toString().contains(filmId.toString()), Type.LIBRARY, filmId
                )
            }
        }
    }

    fun checkWatchLaterItem(filmId: Int) {
        firebase.checkWatchLaterItem(phone, filmId) { filmList ->
            if (filmList == null) {
                watchLaterList[title] = filmId
                checkFilmState(false, Type.WATCH, filmId)
            } else {
                watchLaterList = filmList
                checkFilmState(
                    filmList.values.toString().contains(filmId.toString()), Type.WATCH, filmId
                )
            }
        }
    }

    fun convertTime(time: Int): String {
        val hours = (time / 60)
        val minutes = time - hours * 60
        return when (hours) {
            0 -> "$hours $HOURS $minutes $MINUTES"
            1 -> "$hours $HOUR $minutes $MINUTES"
            2 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
            3 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
            4 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
            5 -> "$hours $HOURS $minutes $MINUTES"
            else -> "$hours $HOURS $minutes $MINUTES"
        }
    }

    private fun checkFilmState(state: Boolean, type: Type, filmId: Int) {
        when(type) {
            Type.WATCH -> {
                if(state) addToWatchLater(filmId)
                else viewState.addToWatchLaterError()
            }
                Type.LIBRARY -> {
                    if(state) addToLibrary(filmId)
                    else viewState.addToLibraryError()
                }
        }

    }

    private fun addToLibrary(film: Int) {
        libraryList[title] = film
        firebase.addToLibrary(phone, libraryList)
    }

    private fun addToWatchLater(film: Int) {
        watchLaterList[title] = film
        firebase.addToWatchLater(phone, watchLaterList)
    }


    enum class Type {
        LIBRARY,
        WATCH
    }

    companion object {
        const val HOURS = "Часов"
        const val HOUR = "Час"
        const val OTHER_HOUSE = "часа"
        const val MINUTES = "Минут"
    }
}
