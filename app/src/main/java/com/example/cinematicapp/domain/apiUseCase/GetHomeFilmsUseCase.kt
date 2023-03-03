package com.example.cinematicapp.domain.apiUseCase

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel

interface GetHomeFilmsUseCase {

    fun getAllFilms(film: Array<String>, callBack: (item: ResponseModel) -> Unit)
}