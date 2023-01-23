package com.example.cinematicapp.presentation.ui.registration.code

import com.google.firebase.auth.PhoneAuthOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationCodeView: MvpView {
    fun confirmCodeSuccessToast()
    fun confirmCodeFailToast()
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentCode(option: PhoneAuthOptions.Builder)
}