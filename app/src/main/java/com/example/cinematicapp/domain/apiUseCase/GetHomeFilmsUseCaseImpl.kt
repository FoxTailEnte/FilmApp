package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import javax.inject.Inject

class GetHomeFilmsUseCaseImpl @Inject constructor(
    private val homeFilms: GetHomeFilms,
) : GetHomeFilmsUseCase {

    override fun getAllFilms(film: Array<String>, callBack: (item: ResponseModel) -> Unit) {
        homeFilms.getAllFilms(film) {
            callBack.invoke(it)
        }
    }
}