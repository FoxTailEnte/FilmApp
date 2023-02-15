package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import android.app.Activity
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.repository.utils.Constants
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ForgotPasswordPresenter @Inject constructor(
    private val firebase: FireBaseSmsUseCase
) : MvpPresenter<ForgotPasswordView>() {

    fun checkUserPhone(phone: String) {
        firebase.authUser(phone) {
            when (it) {
                false -> viewState.sentSms(phone)
                else -> viewState.userNotRegister()
            }
        }
    }

    fun sentSms(phone: String, activity: Activity) {
        firebase.sentSms(phone, activity) {
            when (it) {
                Constants.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess(phone, it)
            }

        }
    }
}