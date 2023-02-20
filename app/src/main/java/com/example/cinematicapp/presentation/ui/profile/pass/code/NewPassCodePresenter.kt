package com.example.cinematicapp.presentation.ui.profile.pass.code

import android.app.Activity
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.repository.utils.Constants
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewPassCodePresenter @Inject constructor(
    private val firebase: FireBaseSmsUseCase,
    private val pref: SharedPrefUseCase
) : MvpPresenter<NewPassCodeCodeView>() {

    fun getNumber() = pref.getUserPhone()

    fun authUser(phone: String, activity: Activity) {
        firebase.sentSms(phone, activity) {
            when (it) {
                Constants.FAIL -> viewState.verificationFailed()
                else -> viewState.sentCodeSuccess()
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