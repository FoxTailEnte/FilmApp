package com.example.cinematicapp.presentation.ui.profile.pass.newPass

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewPassNewPassView : BaseView {
    fun setUserPass()
}