package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code

import com.example.cinematicapp.presentation.base.BaseView
import com.google.firebase.auth.PhoneAuthOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ForgotPasswordCodeView : BaseView {
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentCode(option: PhoneAuthOptions.Builder)
    fun confirmCodeSuccess()
    fun confirmCodeFailToast()
}