package com.example.cinematicapp.presentation.ui.profile.notifications

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType (OneExecutionStateStrategy::class)
interface NotificationsView: BaseView {
}