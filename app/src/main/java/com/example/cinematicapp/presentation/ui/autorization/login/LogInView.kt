package com.example.cinematicapp.presentation.ui.autorization.login

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LogInView: MvpView {
    fun userNotFound()
    fun userDataError()
    fun userAuthComplete()
}