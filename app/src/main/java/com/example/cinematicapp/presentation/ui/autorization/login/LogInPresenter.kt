package com.example.cinematicapp.presentation.ui.autorization.login

import android.util.Log
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LogInPresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val firebase: FireBaseSmsUseCase
) : BasePresenter<LogInView>() {

    fun authUser(phone: String, pass: String) {
        firebase.authUser(phone, pass) {
            Log.d("MyLog",phone)
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

    fun savePhone(phone: String) {
        pref.saveUserPhone(phone)
    }
}
