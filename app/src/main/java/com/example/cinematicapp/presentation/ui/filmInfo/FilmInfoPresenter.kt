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
    private var libraryList = mutableListOf<Int>()
    private var watchLaterList = mutableListOf<Int>()
    fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getIdFilms(film: String?) {
        if (film != null) {
            getHomeFilmsUseCase.getIdFilms(film).singleRequest { response ->
                viewState.setFilmInfo(response)
                viewState.submitList(response.persons)
            }
        }
    }

    fun checkLibraryItem(id: Int, action: (Boolean) -> Unit) {
        firebase.checkLibraryItem(phone, id) {
            if (it == null) {
                libraryList.clear()
                action.invoke(false)
            } else {
                libraryList.clear()
                it.forEach { film ->
                    libraryList.add(film)
                }
                action.invoke(it.toString().contains(id.toString()))
            }
        }
    }

    fun checkWatchLaterItem(id: Int, action: (Boolean) -> Unit) {
        firebase.checkWatchLaterItem(phone, id) {
            if (it == null) {
                watchLaterList.clear()
                action.invoke(false)
            } else {
                watchLaterList.clear()
                it.forEach { film ->
                    watchLaterList.add(film)
                }
                action.invoke(it.toString().contains(id.toString()))
            }
        }
    }

    fun addToLibrary(film: Int) {
        libraryList.add(film)
        firebase.addToLibrary(phone, libraryList)
    }

    fun addToWatchLater(film: Int) {
        watchLaterList.add(film)
        firebase.addToWatchLater(phone, watchLaterList)
    }

}
