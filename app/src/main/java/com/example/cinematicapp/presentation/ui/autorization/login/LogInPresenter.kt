package com.example.cinematicapp.presentation.ui.autorization.login

import com.example.cinematicapp.repository.data.sharedpref.SaveUserAuthStatus
import com.example.cinematicapp.repository.network.firebase.FireBase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LogInPresenter @Inject constructor(
    private val pref: SaveUserAuthStatus,
    private val firebase: FireBase
) : MvpPresenter<LogInView>() {

    fun authUser(phone: String, pass: String) {
        firebase.authUser(phone, pass) {
            when (it) {
                null -> viewState.userNotFound()
                true -> {
                    pref.addSignInUserStatus(true)
                    viewState.userAuthComplete()
                }
                false -> viewState.userDataError()
            }
        }
    }

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()
}
