package com.example.cinematicapp.presentation.ui.registration.number

import android.app.Activity
import com.example.cinematicapp.repository.network.firebase.FireBase
import com.example.cinematicapp.repository.utils.Constants
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class RegistrationNumberPresenter @Inject constructor(
    private val firebase: FireBase
) : MvpPresenter<RegistrationNumberView>() {

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
                Constants.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess(phone,it)
            }
        }
    }
}

