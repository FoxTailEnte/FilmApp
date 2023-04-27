package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import io.reactivex.Observable

interface PassengerSource {

    fun getRandomFilm(film: Array<String>, type: String): Observable<PagingData<BaseFilmInfoResponse>>

    fun getFilmsById(film: Array<String>, type: String, listSize: Int): Observable<PagingData<BaseFilmInfoResponse>>

    fun getGenresFilms(film: Array<String>, type: String): Observable<PagingData<BaseFilmInfoResponse>>
}