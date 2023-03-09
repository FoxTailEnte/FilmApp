package com.example.cinematicapp.presentation.ui.labrary

import com.example.cinematicapp.presentation.base.BaseView
import com.google.firebase.auth.PhoneAuthOptions
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LibraryView : BaseView {

}