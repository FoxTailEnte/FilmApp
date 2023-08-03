package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ForgotPasswordNewPassPresenter @Inject constructor(
    private val firebase: FireBaseDataUseCase
) : BasePresenter<ForgotPasswordNewPassView>() {

    fun addNewPass(phone: String, pass: String) {
        firebase.addNewPass(phone,pass)
        viewState.setUserPass()
    }

    fun validateText(view: TextInputLayout, text: String) = view.validate(text)
}