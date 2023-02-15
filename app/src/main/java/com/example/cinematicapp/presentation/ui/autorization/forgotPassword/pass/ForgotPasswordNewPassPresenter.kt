package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ForgotPasswordNewPassPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : MvpPresenter<ForgotPasswordNewPassView>() {

    fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone,pass)
        viewState.setUserPass()
    }
}