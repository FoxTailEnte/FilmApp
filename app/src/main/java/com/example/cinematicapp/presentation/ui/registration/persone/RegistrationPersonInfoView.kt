package com.example.cinematicapp.presentation.ui.registration.persone

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RegistrationPersonInfoView: MvpView {
}