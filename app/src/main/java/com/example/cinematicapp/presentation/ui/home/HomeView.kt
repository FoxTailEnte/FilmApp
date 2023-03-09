package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface HomeView: BaseView {
    fun submitList(items: ResponseModel)
}