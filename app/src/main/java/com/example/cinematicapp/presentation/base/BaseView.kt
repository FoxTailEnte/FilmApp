package com.example.cinematicapp.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseView: MvpView {
    fun setLoadingState(isLoading: Boolean)

}