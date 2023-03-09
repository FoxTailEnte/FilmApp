package com.example.cinematicapp.presentation.ui.autorization.registration.code

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface RegistrationCodeView: BaseView {
    fun confirmCodeSuccess()
    fun confirmCodeFailToast()
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
}