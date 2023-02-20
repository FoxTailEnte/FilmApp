package com.example.cinematicapp.presentation.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView: MvpView {
    fun signOutComplete()
    fun navigateToProfileInformation()
    fun navigateToNewPass()
}