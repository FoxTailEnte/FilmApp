package com.example.cinematicapp.presentation.ui.autorization.login

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LogInPresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val firebase: FireBaseSmsUseCase
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
    fun savePhone(phone: String) {
        pref.saveUserPhone(phone)
    }
}
