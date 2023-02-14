package com.example.cinematicapp.presentation.ui.registration.number

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationNumberView: MvpView {
    fun sentCodeSuccess(phone: String, id: String)
    fun verificationFailed()
    fun userBeRegister()
    fun sentSms(phone: String)
}