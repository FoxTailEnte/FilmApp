package com.example.cinematicapp.presentation.base

import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
abstract class BasePresenter<T: BaseView>: MvpPresenter<T>(),ResponseHandler<T> {
    override val compositeDisposable by lazy { CompositeDisposable() }
    override val view: T by lazy { viewState }
}