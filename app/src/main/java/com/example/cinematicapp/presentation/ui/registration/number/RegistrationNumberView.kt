package com.example.cinematicapp.presentation.ui.registration.number

import com.google.firebase.auth.PhoneAuthOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationNumberView: MvpView {
    fun sentCode(option: PhoneAuthOptions.Builder)
    fun sentCodeSuccess(phone: String, id: String)
    fun verificationFailed()
}