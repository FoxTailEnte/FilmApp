package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase
) : MvpPresenter<HomeView>() {

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()
}
