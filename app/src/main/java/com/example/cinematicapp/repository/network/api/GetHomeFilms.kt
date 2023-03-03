package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel

interface GetHomeFilms {

    fun getAllFilms(film: Array<String>, callBack: (item: ResponseModel) -> Unit)
}