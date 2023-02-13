package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code

import com.google.firebase.auth.PhoneAuthOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ForgotPasswordCodeView : MvpView {
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentCode(option: PhoneAuthOptions.Builder)
    fun confirmCodeSuccessToast()
    fun confirmCodeFailToast()
}