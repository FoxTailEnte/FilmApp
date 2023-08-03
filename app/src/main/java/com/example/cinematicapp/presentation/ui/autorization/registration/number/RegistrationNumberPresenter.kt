package com.example.cinematicapp.presentation.ui.autorization.registration.number

import android.app.Activity
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.ViewUtils.validate
import com.google.android.material.textfield.TextInputLayout
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class RegistrationNumberPresenter @Inject constructor(
    private val firebase: FireBaseSmsUseCase
) : BasePresenter<RegistrationNumberView>() {

    fun checkUserPhone(phone: String) {
        firebase.authUser(phone) {
            when (it) {
                true -> viewState.sentSms(phone)
                else -> viewState.userBeRegister()
            }
        }
    }

    fun sendSms(phone: String, activity: Activity) {
        firebase.sentSms(phone, activity) {
            when (it) {
                Constants.FireBase.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess(phone, it)
            }
        }
    }

    fun validateText(view: TextInputLayout, text: String) = view.validate(text)
}

