package com.example.cinematicapp.presentation.ui.profile.pass.code

import com.google.firebase.auth.PhoneAuthOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewPassCodeCodeView : MvpView {
    fun verificationFailed()
    fun sentCodeSuccess()
    fun sentCode(option: PhoneAuthOptions.Builder)
    fun confirmCodeSuccess()
    fun confirmCodeFailToast()
}