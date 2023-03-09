package com.example.cinematicapp.presentation.ui.profile

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView: BaseView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun signOutComplete()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToProfileInformation()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToNewPass()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToNotifications()
}