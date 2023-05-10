package com.example.cinematicapp.presentation.ui.later

import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface WatchLaterView : BaseView {
    fun submitList(items: PagingData<BaseFilmInfoResponse>)

    fun getSearchText()

    fun scrollRcMain(position: Int)
}