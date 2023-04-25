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

    fun checkLibraryItem(id: Int, action:(Boolean) -> Unit) {
        firebase.checkLibraryItem(phone, id) {
            action.invoke(it)
        }
    }

    fun checkWatchLaterItem(id: Int, action:(Boolean) -> Unit) {
        firebase.checkWatchLaterItem(phone, id) {
            action.invoke(it)
        }
    }

    fun addToLibrary(id: Int, name: String) {
        firebase.addToLibrary(phone, id, name)
    }

    fun addToWatchLater(id: Int, name: String) {
        firebase.addToWatchLater(phone, id, name)
    }

}
