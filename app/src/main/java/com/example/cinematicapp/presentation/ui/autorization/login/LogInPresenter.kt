package com.example.cinematicapp.presentation.ui.autorization.login

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.ViewUtils.validatePass
import com.example.cinematicapp.repository.utils.ViewUtils.validatePhone
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LogInPresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val firebase: FireBaseSmsUseCase
) : BasePresenter<LogInView>() {

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

    fun savePhone(phone: String) {
        pref.saveUserPhone(phone)
    }

    fun validateNumber(view: TextInputLayout, text: String): Boolean = view.validatePhone(text)
    fun validatePass(view: TextInputLayout, text: String): Boolean = view.validatePass(text)
}
