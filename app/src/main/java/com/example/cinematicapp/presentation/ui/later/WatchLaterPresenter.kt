package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.adapters.main.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.presentation.ui.home.HomePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.SearchUtils
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class WatchLaterPresenter @Inject constructor(
    private val dataSource: PassengerSource,
    private val fireStore: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<WatchLaterView>() {

    var searchText: String = Constants.FilmInfo.EMPTY_TEXT
    private val mDisposable = CompositeDisposable()
    private val currentList = mutableListOf<String>()
    private var rcColorState: Boolean = true
    private var newPosition: Int = 0
    private var oldPosition: Int = 0
    private var filterList = listOf<CheckedItemModel>()
    private val searchTextList = mutableListOf<String>()
    private val genresList = mutableListOf<String>()
    private val yearsList = mutableListOf<String>()
    private val ratingList = mutableListOf<String>()
    private val countryList = mutableListOf<String>()
    private lateinit var phone: String

    private fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getLibraryList() {
        fireStore.getWatchLater(getUserPhone()) { filmList ->
            if (filmList?.keys.isNullOrEmpty()) {
                parseLibraryListToResponse(filmList)
                viewState.setPlaceHolderEmptyList()
            }
            if (filmList?.size != currentList.size && !filmList?.keys.isNullOrEmpty()) {
                parseLibraryListToResponse(filmList)
                getWatchLaterFilms()
            }
        }
    }

    fun saveMainRcState(state: Boolean) {
        rcColorState = state
        newPosition = 0
        oldPosition = 0
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
                        HomePresenter.FIVE_RATING -> prepareRatingList.add("5.0-9.9")
                        HomePresenter.SIX_RATING -> prepareRatingList.add("6.0-9.9")
                        HomePresenter.SEVEN_RATING -> prepareRatingList.add("7.0-9.9")
                        HomePresenter.EIGHT_RATING -> prepareRatingList.add("8.0-9.9")
                        HomePresenter.NINE_RATING -> prepareRatingList.add("9.0-9.9")
                        else -> Unit
                    }
                    val currentRatingList = SearchUtils.setRating(prepareRatingList.distinct())
                    ratingList.clear()
                    ratingList.addAll(currentRatingList)
                }
            }
        }
        viewState.scrollToPosition()
    }

    fun getFilterItemsForDialogFragment(): List<CheckedItemModel> {
        return filterList
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

    fun getFilmsWithText(text: List<String> = listOf()) {
        searchTextList.clear()
        searchTextList.addAll(text)
        getWatchLaterFilms()
        viewState.scrollToPosition()
    }

    private fun parseLibraryListToResponse(list: HashMap<String, Int>?) {
        currentList.clear()
        list?.forEach {
            currentList.add(it.value.toString())
        }
    }

    private fun setSearchFilterMap(): MutableMap<String, Array<String>> {
        return mutableMapOf(
            Constants.Request.ID to currentList.toTypedArray(),
            Constants.Request.SEARCH to searchTextList.toTypedArray(),
            Constants.Request.GENRES_FILTER to genresList.toTypedArray(),
            Constants.Request.YEARS_FILTER to yearsList.toTypedArray(),
            Constants.Request.RATING_FILTER to ratingList.toTypedArray(),
            Constants.Request.COUNTRY_FILTER to countryList.toTypedArray()
        )
    }

    fun getWatchLaterFilms() {
        if (currentList.isNotEmpty()) {
            mDisposable.add(dataSource.getRandomFilm(setSearchFilterMap()).subscribe {
                viewState.submitList(it)
                viewState.setPlaceHolder()
            })
        }
    }

    fun getFilmsWithGenres(genres: List<String> = listOf()) {
        clearAllFiltersForDialogFragment()
        clearFilterForResponse()
        genresList.addAll(genres)
        rcColorState = true
        setFullFilterState()
        getWatchLaterFilms()
        viewState.scrollToPosition()
    }

    fun clearOldFilters() {
        filterList = listOf()
        genresList.clear()
        yearsList.clear()
        ratingList.clear()
        countryList.clear()
    }

    private fun clearFilterForResponse() {
        genresList.clear()
        yearsList.clear()
        ratingList.clear()
        countryList.clear()
    }

    private fun clearAllFiltersForDialogFragment() {
        filterList = listOf()
    }

    fun initAdapters() {
        viewState.initRcMain(rcColorState, newPosition, oldPosition)
        viewState.initRc()
        setFullFilterState()
    }

    private fun setFullFilterState() {
        viewState.setFullFilterColor(rcColorState)
    }
}