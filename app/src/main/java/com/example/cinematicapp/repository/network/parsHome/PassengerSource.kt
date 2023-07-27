package com.example.cinematicapp.repository.network.parsHome

import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import io.reactivex.Observable

interface PassengerSource {

    fun getRandomFilm(film: MutableMap<String, Array<String>>): Observable<PagingData<BaseFilmInfoResponse>>
}