package com.example.cinematicapp.repository.network.api

import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomeFilmsImpl @Inject constructor(
    private val api: Api
): GetHomeFilms {

    override fun getAllFilms(film: Array<String>, callBack: (item: ResponseModel) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                api.getRandomFilm(film)
            }.apply {
                callBack.invoke(this)
            }
        }
    }

}