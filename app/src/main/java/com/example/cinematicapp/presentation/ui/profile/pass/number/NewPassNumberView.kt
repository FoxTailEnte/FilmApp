package com.example.cinematicapp.presentation.ui.profile.pass.number

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewPassNumberView: BaseView {
    fun userNotRegister()
    fun verificationFailed()
    fun sentCodeSuccess(phone: String, id: String)
    fun sentSms(phone: String)
}