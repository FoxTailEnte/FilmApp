package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ForgotPasswordNewPassView : MvpView {
    fun setUserPass()
}