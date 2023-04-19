package com.example.cinematicapp.repository.network

import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import io.reactivex.Observable

interface PassengerSource {

    fun getRandomFilm(film: Array<String>, genres: Boolean): Observable<PagingData<BaseFilmInfoResponse>>

    fun getGenresFilms(film: Array<String>, genres: Boolean): Observable<PagingData<BaseFilmInfoResponse>>
}