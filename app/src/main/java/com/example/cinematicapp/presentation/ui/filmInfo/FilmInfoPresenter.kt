package com.example.cinematicapp.presentation.ui.filmInfo

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FilmInfoPresenter @Inject constructor(
    private val getHomeFilmsUseCase: GetHomeFilmsUseCase
) : BasePresenter<FilmInfoView>() {

    fun getIdFilms(film: String?) {
        if (film != null) {
            getHomeFilmsUseCase.getIdFilms(film).singleRequest { response ->
                viewState.setFilmInfo(response)
                viewState.submitList(response.persons)
            }
        }
    }

}
