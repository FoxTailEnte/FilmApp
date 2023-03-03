package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase
) : MvpPresenter<HomeView>() {

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun getItems() {
        val currentFilm = arrayOf("Игра")
        getHomeFilmsUseCase.getAllFilms(currentFilm) {
            viewState.submitList(it)
        }
    }
}
