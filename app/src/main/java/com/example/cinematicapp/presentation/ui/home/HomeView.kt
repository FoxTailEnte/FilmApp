package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface HomeView: MvpView {
    fun submitList(items: ResponseModel)
}