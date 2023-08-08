package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.adapters.main.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.SearchUtils
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val pref: SharedPrefUseCase,
    private val dataSource: PassengerSource
) : BasePresenter<HomeView>() {

    var searchText: String = Constants.FilmInfo.EMPTY_TEXT
    private val mDisposable = CompositeDisposable()
    private var newPosition: Int = 0
    private var oldPosition: Int = 0
    private var rcColorState: Boolean = true
    private var filterList = listOf<CheckedItemModel>()
    private val searchTextList = mutableListOf<String>()
    private val genresList = mutableListOf<String>()
    private val yearsList = mutableListOf<String>()
    private val ratingList = mutableListOf<String>()
    private val countryList = mutableListOf<String>()

    fun checkUserAuthStatus(): Boolean = pref.getSignInUserStatus()

    fun getFilmWithFilters() {
        mDisposable.add(dataSource.getRandomFilm(getFiltersForRequest())
            .subscribe {
                viewState.submitList(it)
                viewState.setPlaceHolder()
            })
    }

    private fun getFiltersForRequest(): MutableMap<String, Array<String>> {
        return mutableMapOf(
            Constants.Request.SEARCH to searchTextList.toTypedArray(),
            Constants.Request.GENRES_FILTER to genresList.toTypedArray(),
            Constants.Request.YEARS_FILTER to yearsList.toTypedArray(),
            Constants.Request.RATING_FILTER to ratingList.toTypedArray(),
            Constants.Request.COUNTRY_FILTER to countryList.toTypedArray()
        )
    }

    fun saveFullFilters(filterItems: List<CheckedItemModel>) {
        filterList = filterItems
        val prepareRatingList = mutableListOf<String>()
        filterItems.forEach {
            when (it.mainFilter) {
                Constants.Request.GENRES_FILTER -> genresList.add(it.fullFilter.lowercase())
                Constants.Request.COUNTRY_FILTER -> countryList.add(it.fullFilter)
                Constants.Request.YEARS_FILTER -> {
                    val years = SearchUtils.setYears(it.fullFilter)
                    yearsList.addAll(years)
                }

                Constants.Request.RATING_FILTER -> {
                    when (it.fullFilter) {
                        FIVE_RATING -> prepareRatingList.add("5.0-9.9")
                        SIX_RATING -> prepareRatingList.add("6.0-9.9")
                        SEVEN_RATING -> prepareRatingList.add("7.0-9.9")
                        EIGHT_RATING -> prepareRatingList.add("8.0-9.9")
                        NINE_RATING -> prepareRatingList.add("9.0-9.9")
                        else -> Unit
                    }
                    val currentRatingList = SearchUtils.setRating(prepareRatingList.distinct())
                    ratingList.clear()
                    ratingList.addAll(currentRatingList)
                }
            }
        }
    }

    fun saveMainRcState(state: Boolean) {
        rcColorState = state
        newPosition = 0
        oldPosition = 0
    }

    fun clearOldFilters() {
        filterList = listOf()
        genresList.clear()
        yearsList.clear()
        ratingList.clear()
        countryList.clear()
    }

    fun getFilmsWithGenres(genres: List<String> = listOf()) {
        genresList.addAll(genres)
        rcColorState = true
        setFullFilterState()
        getFilmWithFilters()
    }

    fun getFilmsWithText(text: List<String> = listOf()) {
        searchTextList.clear()
        searchTextList.addAll(text)
        getFilmWithFilters()
    }

    fun saveMainPosition(position: MainRcViewAdapter.CallBack) {
        when (position) {
            is MainRcViewAdapter.CallBack.NewPosition -> {
                newPosition = position.position
            }

            is MainRcViewAdapter.CallBack.OldPosition -> {
                oldPosition = position.position
            }

            else -> Unit
        }
    }

    fun getFilterItemsForDialogFragment(): List<CheckedItemModel> {
        return filterList
    }

    fun initAdapters() {
        viewState.initRcMain(rcColorState, newPosition, oldPosition)
        viewState.initRc()
        setFullFilterState()
    }

    private fun setFullFilterState() {
        viewState.setFullFilterColor(rcColorState)
    }

    companion object {
        const val NINE_RATING = "Больше 9"
        const val EIGHT_RATING = "Больше 8"
        const val SEVEN_RATING = "Больше 7"
        const val SIX_RATING = "Больше 6"
        const val FIVE_RATING = "Больше 5"
    }
}
