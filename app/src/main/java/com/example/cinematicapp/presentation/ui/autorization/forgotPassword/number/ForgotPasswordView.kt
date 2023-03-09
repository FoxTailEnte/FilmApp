package com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface ForgotPasswordView : BaseView {
    fun userNotRegister()
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentSms(phone: String)
}