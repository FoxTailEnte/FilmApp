package com.example.cinematicapp.presentation.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun signOutComplete()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToProfileInformation()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToNewPass()
}