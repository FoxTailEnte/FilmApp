package com.example.cinematicapp.presentation.ui.autorization.registration.number

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface RegistrationNumberView: MvpView {
    fun sentCodeSuccess(phone: String, id: String)
    fun verificationFailed()
    fun userBeRegister()
    fun sentSms(phone: String)
}