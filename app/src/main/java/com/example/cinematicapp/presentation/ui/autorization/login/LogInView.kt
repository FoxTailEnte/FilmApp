package com.example.cinematicapp.presentation.ui.autorization.login

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LogInView: BaseView {
    fun userNotFound()
    fun userDataError()
    fun userAuthComplete()
}