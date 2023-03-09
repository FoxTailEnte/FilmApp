package com.example.cinematicapp.presentation.base

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
abstract class BasePresenter<T: BaseView>: MvpPresenter<T>() {
}