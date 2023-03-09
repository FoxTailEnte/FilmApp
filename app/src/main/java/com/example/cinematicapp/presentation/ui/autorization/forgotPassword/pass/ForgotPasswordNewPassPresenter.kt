package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ForgotPasswordNewPassPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : BasePresenter<ForgotPasswordNewPassView>() {

    fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone,pass)
        viewState.setUserPass()
    }
}