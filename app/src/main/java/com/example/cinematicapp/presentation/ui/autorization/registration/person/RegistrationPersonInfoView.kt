package com.example.cinematicapp.presentation.ui.autorization.registration.person

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationPersonInfoView: BaseView {
    fun completeRegistration()
}