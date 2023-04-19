package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.PassengerSource
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val dataSource: PassengerSource
) : BasePresenter<HomeView>() {

    private val mDisposable = CompositeDisposable()

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun getRandomFilms(film: Array<String>?) {
        if(film != null) {
            mDisposable.add(dataSource.getRandomFilm(film,false).subscribe {
                viewState.submitList(it)
            })
        }
    }

    fun getGenresFilms(film: Array<String>?) {
        if(film != null) {
            mDisposable.add(dataSource.getGenresFilms(film,true).subscribe {
                viewState.submitList(it)
            })
        }
    }
}
