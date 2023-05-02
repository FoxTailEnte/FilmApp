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
    private var libraryList = hashMapOf<String,Int>()
    private var watchLaterList = hashMapOf<String,Int>()
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

    fun checkLibraryItem(filmId: Int, action: (Boolean) -> Unit) {
        firebase.checkLibraryItem(phone, filmId) { filmList ->
            if (filmList == null) {
                libraryList[title] = filmId
                action.invoke(false)
            } else {
                libraryList = filmList
                action.invoke(filmList.values.toString().contains(filmId.toString()))
            }
        }
    }

    fun checkWatchLaterItem(filmId: Int, action: (Boolean) -> Unit) {
        firebase.checkWatchLaterItem(phone, filmId) { filmList ->
            if (filmList == null) {
                watchLaterList[title] = filmId
                action.invoke(false)
            } else {
                watchLaterList = filmList
                action.invoke(filmList.values.toString().contains(filmId.toString()))
            }
        }
    }

    fun addToLibrary(film: Int) {
        libraryList[title] = film
        firebase.addToLibrary(phone, libraryList)
    }

    fun addToWatchLater(film: Int) {
        watchLaterList[title] = film
        firebase.addToWatchLater(phone, watchLaterList)
    }

}
