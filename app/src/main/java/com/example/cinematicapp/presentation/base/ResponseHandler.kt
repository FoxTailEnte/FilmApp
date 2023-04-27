package com.example.cinematicapp.presentation.base

import android.util.Log
import androidx.paging.PagingData
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

interface ResponseHandler<out T : BaseView> {
    val compositeDisposable: CompositeDisposable
    val view: T

    fun Single<PagingData<BaseFilmInfoResponse>>.regularRequest(
        successAction: ((PagingData<BaseFilmInfoResponse>) -> Unit)?,
    ) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("MyLog", it.toString())
                successAction?.invoke(it)
            }, { exception ->

            }).addTo(compositeDisposable)

    fun Single<BaseIdFilmResponse>.singleRequest(
        successAction: ((BaseIdFilmResponse) -> Unit)?,
    ) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successAction?.invoke(it)
            }, { exception ->

            }).addTo(compositeDisposable)

    fun Single<BaseFilmResponse>.singlerRequest(
        successAction: ((BaseFilmResponse) -> Unit)?,
    ) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }
            .subscribe({
                successAction?.invoke(it)
            }, { exception ->
            }).addTo(compositeDisposable)
}