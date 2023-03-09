package com.example.cinematicapp.presentation.ui.profile.person

import com.example.cinematicapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfilePersonView: BaseView {
    fun addNewUser(phone: String)
    fun addNewUserSuccess()
}