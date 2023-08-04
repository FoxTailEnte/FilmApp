package com.example.cinematicapp.presentation.ui.filmInfo

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.homeFilm.models.Trailer
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.Constants
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FilmInfoPresenter @Inject constructor(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase,
    private val firebase: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<FilmInfoView>() {

    private lateinit var phone: String
    private var libState: Boolean = false
    private var watchState: Boolean = false
    private var libraryList = hashMapOf<String, Int>()
    private var watchLaterList = hashMapOf<String, Int>()
    var trailer: List<Trailer> = listOf()
    private var film: Int = 0
    private var title = Constants.FilmInfo.EMPTY_TEXT


    fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getIdFilms(film: String?) {
        if (film != null) {
            getHomeFilmsUseCase.getIdFilms(film).singleRequest { response ->
                trailer = response.videos?.trailers ?: emptyList()
                title = response.name
                viewState.setFilmInfo(response)
                viewState.submitList(response.persons)
            }
        }
    }

    fun getLibraryItem(filmId: Int) {
        film = filmId
        firebase.checkLibraryItem(phone, filmId) { filmList ->
            if (filmList == null) {
                addLibrary()
                libraryList[title] = filmId
                viewState.setFilmLibState(false)
            } else {
                libraryList = filmList
                checkFilmLibState(
                    filmList.values.toString()
                        .contains(film.toString())
                )
            }
        }
    }

    fun getWatchLaterItem(filmId: Int) {
        film = filmId
        firebase.checkWatchLaterItem(phone, filmId) { filmList ->
            if (filmList == null) {
                addWatchLater()
                watchLaterList[title] = filmId
                viewState.setFilmWatchState(false)
            } else {
                watchLaterList = filmList
                checkFilmWatchState(
                    filmList.values.toString()
                        .contains(film.toString())
                )
            }
        }
    }

    private fun checkFilmLibState(state: Boolean) {
        libState = state
        if (state) viewState.setFilmLibState(true)
        else viewState.setFilmLibState(false)
    }

    private fun checkFilmWatchState(state: Boolean) {
        watchState = state
        if (state) viewState.setFilmWatchState(true)
        else viewState.setFilmWatchState(false)
    }

    fun convertTime(time: Int?): String {
        return if (time == null) {
            "_ Часов _ Минут"
        } else {
            val hours = (time / 60).toInt()
            val minutes = time - hours * 60
            when (hours) {
                0 -> "$hours $HOURS $minutes $MINUTES"
                1 -> "$hours $HOUR $minutes $MINUTES"
                2 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
                3 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
                4 -> "$hours $OTHER_HOUSE $minutes $MINUTES"
                5 -> "$hours $HOURS $minutes $MINUTES"
                else -> "$hours $HOURS $minutes $MINUTES"
            }
        }
    }

    fun addFilmToLib(title: String) {
        if (libState) {
            deleteLibItem()
        } else {
            updateLibrary(film, title)
        }

    }

    fun addFilmToWatch(title: String) {
        if (watchState) {
            deleteWatchItem()
        } else {
            updateWatchLater(film, title)
        }
    }

    private fun addLibrary() {
        firebase.addLibrary(phone,libraryList)
    }

    private fun addWatchLater() {
        firebase.addWatchLater(phone,watchLaterList)
    }

    private fun updateLibrary(film: Int, titleFilm: String) {
        libraryList[titleFilm] = film
        firebase.updateLibrary(phone, libraryList)
        libState = true
        viewState.setFilmLibState(true)
    }

    private fun updateWatchLater(film: Int, titleFilm: String) {
        watchLaterList[titleFilm] = film
        watchState = true
        firebase.updateWatchLater(phone, watchLaterList)
        viewState.setFilmWatchState(true)
    }

    private fun deleteLibItem() {
        libraryList.remove(title)
        firebase.deleteLibItem(phone, libraryList)
        libState = false
        viewState.setFilmLibState(false)
    }

    private fun deleteWatchItem() {
        watchLaterList.remove(title)
        firebase.deleteWatchItem(phone, watchLaterList)
        watchState = false
        viewState.setFilmWatchState(false)
    }

    companion object {
        const val HOURS = "Часов"
        const val HOUR = "Час"
        const val OTHER_HOUSE = "часа"
        const val MINUTES = "Минут"
    }
}
