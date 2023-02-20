package com.example.cinematicapp.presentation.ui.profile.pass.newPass

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewPassNewPassView : MvpView {
    fun setUserPass()
}