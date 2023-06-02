package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val dataSource: PassengerSource
) : BasePresenter<HomeView>() {

    private val mDisposable = CompositeDisposable()
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()
    private val checkedItemGenre = mutableListOf<String>()
    private val checkedItemYears = mutableListOf<String>()
    private val checkedItemRating = mutableListOf<String>()
    private val checkedItemCountry = mutableListOf<String>()
    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun saveFilters(item: CheckedItemModel) {
        when(item.type) {
            Constants.GENRES_FILTER -> {
                if(item.checked) checkedItemGenre.add(item.item)
                else checkedItemGenre.remove(item.item)
            }
            Constants.YEARS_FILTER -> {
                if(item.checked) checkedItemYears.add(item.item)
                else checkedItemYears.remove(item.item)
            }
            Constants.RATING_FILTER -> {
                if(item.checked) checkedItemRating.add(item.item)
                else checkedItemRating.remove(item.item)
            }
            Constants.COUNTRY_FILTER -> {
                if(item.checked) checkedItemCountry.add(item.item)
                else checkedItemCountry.remove(item.item)
            }
            else -> Unit
        }
    }

    fun clearFilter() {
        checkedItemGenre.clear()
        checkedItemYears.clear()
        checkedItemRating.clear()
        checkedItemCountry.clear()
    }

    fun getFilterItems(): MutableMap<String, MutableList<String>> {
        return mutableMapOf(
            Constants.GENRES_FILTER to checkedItemGenre,
            Constants.YEARS_FILTER to checkedItemYears,
            Constants.RATING_FILTER to checkedItemRating,
            Constants.COUNTRY_FILTER to checkedItemCountry
        )
    }

    fun getFilmWithFilters() {
        mDisposable.add(dataSource.getFilmsWithFilters(arrayOf(""), checkedItemGenre.toTypedArray(), checkedItemYears.toTypedArray(), checkedItemRating.toTypedArray(), checkedItemCountry.toTypedArray(), "Filters").subscribe {
            viewState.submitList(it)
        })
    }

    fun getRandomFilms(film: Array<String>, call: String) {
            currentFilmArray = film
            currentCall = call
            mDisposable.add(dataSource.getRandomFilm(film,"random").subscribe {
                viewState.submitList(it)
            })
    }

    fun getGenresFilms(film: Array<String>, call: String) {
            currentFilmArray = film
            currentCall = call
            mDisposable.add(dataSource.getGenresFilms(film,"genres").subscribe {
                viewState.submitList(it)
            })
    }

    fun getRefreshFilms() {
        when (currentCall) {
            Constants.BASE -> getRandomFilms(currentFilmArray,currentCall)
            Constants.SEARCH -> getRandomFilms(currentFilmArray,currentCall)
            Constants.GENRES -> getGenresFilms(currentFilmArray,currentCall)
        }
    }
}
