package com.example.cinematicapp.presentation.ui.autorization.registration.person

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationPersonInfoView: MvpView {
    fun completeRegistration()
}