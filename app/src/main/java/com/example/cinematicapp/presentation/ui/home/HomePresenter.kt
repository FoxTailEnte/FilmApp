package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase,
) : BasePresenter<HomeView>() {

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun getRandomFilms(film: Array<String>?) {
        if(film != null) {
            getHomeFilmsUseCase.getRandomFilms(film).regularRequest { response ->
                viewState.submitList(response)
            }
        }
    }

    fun getGenresFilms(film: Array<String>?) {
        if(film != null) {
            getHomeFilmsUseCase.getGenresFilms(film).regularRequest { response ->
                viewState.submitList(response)
            }
        }
    }
}
