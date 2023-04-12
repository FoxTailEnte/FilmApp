package com.example.cinematicapp.presentation.base

import com.example.cinematicapp.presentation.adapters.homeFilm.BaseFilmResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

interface ResponseHandler<out T : BaseView> {
    val compositeDisposable: CompositeDisposable
    val view: T

    fun Single<BaseFilmResponse>.regularRequest(
        successAction: ((BaseFilmResponse) -> Unit)?,
    ) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.setLoadingState(true) }
            .doFinally { /*view.setLoadingState(false)*/ }
            .subscribe({
                successAction?.invoke(it)
            }, { exception ->

            }).addTo(compositeDisposable)
}