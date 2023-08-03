package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code

import android.app.Activity
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ForgotPasswordCodePresenter @Inject constructor(
    private val firebase: FireBaseSmsUseCase
) : BasePresenter<ForgotPasswordCodeView>() {

    fun authUser(phone: String, activity: Activity) {
        firebase.sentSms(phone, activity) {
            when (it) {
                Constants.FireBase.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess(phone, it)
            }
        }
    }

    fun enterCode(id: String, code: String) {
        firebase.enterCode(id, code) {
            when (it) {
                true -> viewState.confirmCodeSuccess()
                else -> viewState.confirmCodeFailToast()
            }
        }
    }

    fun validateText(view: TextInputLayout, text: String) = view.validate(text)
}