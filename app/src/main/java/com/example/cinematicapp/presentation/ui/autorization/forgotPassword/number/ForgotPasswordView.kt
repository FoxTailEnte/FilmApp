package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ForgotPasswordView : MvpView {
    fun userNotRegister()
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentSms(phone: String)
}