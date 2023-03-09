package com.example.cinematicapp.presentation.ui.autorization.registration.code

import android.app.Activity
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.Constants
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class RegistrationCodePresenter @Inject constructor(
    private val firebase: FireBaseSmsUseCase
) : BasePresenter<RegistrationCodeView>() {

    fun authUser(phone: String, activity: Activity) {
        firebase.sentSms(phone, activity) {
            when (it) {
                Constants.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess(phone, it)
            }
        }
    }

    fun enterCode(id: String, code: String) {
        firebase.enterCode(id,code) {
            when (it) {
                true -> viewState.confirmCodeSuccess()
                else -> viewState.confirmCodeFailToast()
            }
        }
    }
}