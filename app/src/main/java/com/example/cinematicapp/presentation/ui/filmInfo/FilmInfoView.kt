package com.example.cinematicapp.presentation.ui.filmInfo

import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.Persons
import com.example.cinematicapp.presentation.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface FilmInfoView: BaseView {

    fun setFilmInfo(info: BaseIdFilmResponse)
    fun submitList(items: List<Persons>)
    fun addToLibraryError()
    fun setFilmLibState(state: Boolean)
    fun setFilmWatchState(state: Boolean)
    fun addToWatchLaterError()
}