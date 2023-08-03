package com.example.cinematicapp.presentation.ui.labrary

import android.util.Log
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.adapters.main.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.presentation.ui.home.HomePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import com.example.cinematicapp.repository.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LibraryPresenter @Inject constructor(
    private val dataSource: PassengerSource,
    private val fireStore: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<LibraryView>() {

    private val mDisposable = CompositeDisposable()
    private val currentList = mutableListOf<String>()
    private var newPosition: Int = 0
    private var oldPosition: Int = 0
    private var rcColorState: Boolean = true
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
        if(currentList.isEmpty()){
            fireStore.getLibrary(getUserPhone()) { filmList ->
                parseLibraryListToResponse(filmList)
                getLibraryFilms()
            }
        }
    }

    fun saveFullFilters(filterItems: List<CheckedItemModel>) {
        filterList = filterItems
        val prepareRatingList = mutableListOf<String>()
        filterItems.forEach {
            when (it.mainFilter) {
                Constants.GENRES_FILTER -> genresList.add(it.fullFilter.lowercase())
                Constants.YEARS_FILTER -> yearsList.add(it.fullFilter.lowercase())
                Constants.COUNTRY_FILTER -> countryList.add(it.fullFilter)
                Constants.RATING_FILTER -> {
                    when (it.fullFilter) {
                        HomePresenter.FIVE_RATING -> prepareRatingList.add("5.0-9.9")
                        HomePresenter.SIX_RATING -> prepareRatingList.add("6.0-9.9")
                        HomePresenter.SEVEN_RATING -> prepareRatingList.add("7.0-9.9")
                        HomePresenter.EIGHT_RATING -> prepareRatingList.add("8.0-9.9")
                        HomePresenter.NINE_RATING -> prepareRatingList.add("9.0-9.9")
                        else -> Unit
                    }
                    ratingList.add(prepareRatingList.minOrNull().toString())
                }
            }
        }
        viewState.scrollToPosition()
    }

    fun getFilterItemsForDialogFragment(): List<CheckedItemModel> {
        return filterList
    }

    fun getFilmsWithText(text: List<String> = listOf()) {
        searchTextList.clear()
        searchTextList.addAll(text)
        getLibraryFilms()
        viewState.scrollToPosition()
    }

    private fun parseLibraryListToResponse(list: HashMap<String, Int>?) {
        list?.forEach {
            currentList.add(it.value.toString())
        }
    }

    private fun setSearchFilterMap(): MutableMap<String, Array<String>> {
        return mutableMapOf(
            Constants.ID to currentList.toTypedArray(),
            Constants.SEARCH to searchTextList.toTypedArray(),
            Constants.GENRES_FILTER to genresList.toTypedArray(),
            Constants.YEARS_FILTER to yearsList.toTypedArray(),
            Constants.RATING_FILTER to ratingList.toTypedArray(),
            Constants.COUNTRY_FILTER to countryList.toTypedArray()
        )
    }

    fun getLibraryFilms() {
        mDisposable.add(dataSource.getRandomFilm(setSearchFilterMap()).subscribe({
            viewState.submitList(it)
            viewState.setPlaceHolder()
        }) {
            Log.d("MyLog", "errrrrrrrrrr")

        })
    }

    fun getFilmsWithGenres(genres: List<String> = listOf()) {
        clearAllFiltersForDialogFragment()
        clearFilterForResponse()
        genresList.addAll(genres)
        rcColorState = true
        setFullFilterState()
        getLibraryFilms()
        viewState.scrollToPosition()
    }

    fun clearOldFilters() {
        genresList.clear()
        yearsList.clear()
        ratingList.clear()
        countryList.clear()
    }

    fun saveMainPosition(position: MainRcViewAdapter.CallBack) {
        when(position) {
            is MainRcViewAdapter.CallBack.NewPosition -> {
                newPosition = position.position
            }
            is MainRcViewAdapter.CallBack.OldPosition -> {
                oldPosition = position.position
            }
            else -> Unit
        }
    }

    fun saveMainRcState(state: Boolean) {
        rcColorState = state
        newPosition = 0
        oldPosition = 0
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