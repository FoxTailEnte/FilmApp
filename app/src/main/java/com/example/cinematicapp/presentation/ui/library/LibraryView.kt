package com.example.cinematicapp.presentation.ui.library

import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LibraryView : BaseView {
    fun submitList(items: PagingData<BaseFilmInfoResponse>)
    fun setPlaceHolder()
    fun initRcMain(state: Boolean, newPosition: Int, oldPosition: Int)
    fun scrollToPosition()
    fun initRc()
    fun setFullFilterColor(state:Boolean)
}