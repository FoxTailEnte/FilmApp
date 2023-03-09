package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface WatchLaterView : BaseView {

}