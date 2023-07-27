package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
import com.example.cinematicapp.repository.network.parsHome.PassengerSource
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class WatchLaterPresenter @Inject constructor(
    private val dataSource: PassengerSource,
    private val fireStore: FireBaseDataUseCase,
    private val pref: SharedPrefUseCase
) : BasePresenter<WatchLaterView>() {

    private val mDisposable = CompositeDisposable()
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()
    private lateinit var phone: String
    private lateinit var genresList: Array<String>
    private var watchLaterList: HashMap<String, Int>? = null
    private var rcPosition = 0


    private fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getAllWatchLaterList() {
        fireStore.getWatchLater(getUserPhone()) { filmList ->
            currentCall = ID
            watchLaterList = filmList
            getRandomWatchLaterFilms(getArrayList(filmList), currentCall)
        }
    }

    fun getSearchList(name: String) {
        currentCall = SEARCH
        if(watchLaterList != null) {
            val searchList = watchLaterList!!.filter { it.key.lowercase().startsWith(name.lowercase()) }
            getRandomWatchLaterFilms(getGenresArray(searchList), currentCall)
        }
    }

    fun getGenresWatchLaterFilms(genres: Array<String>) {
        /*currentCall = GENRES_LIBRARY
        genresList = genres
        mDisposable.add(dataSource.getGenresLibraryFilms(currentFilmArray, genresList, currentCall, currentFilmArray.size).subscribe {
            viewState.submitList(it)
        })*/
    }

    private fun getRandomWatchLaterFilms(film: Array<String>, call: String) {
       /* currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getFilmsById(film, currentCall, film.size).subscribe {
            viewState.submitList(it)
        })*/
    }

    private fun getArrayList(map: HashMap<String, Int>?): Array<String> {
        var array: Array<String>? = null
        val currentList: List<Int>? = map?.values?.toList()?.let { ArrayList(it) }
        if (currentList != null) {
            val list = currentList.map { it.toString() }
            array = list.toTypedArray()
        }
        return array ?: arrayOf("")
    }

    private fun getGenresArray(map: Map<String, Int>): Array<String> {
        val idSearch: List<Int> = map.values.toList().let { ArrayList(it) }
        val array = idSearch.map { it.toString() }
        return array.toTypedArray()
    }

    fun setRcMainPosition(position: Int) {
        rcPosition = position
    }

    fun getRcMainPosition() {
        viewState.scrollRcMain(rcPosition)
    }

    fun getCurrentFilmList() {
        when(currentCall) {
            ID -> getAllWatchLaterList()
            SEARCH -> viewState.getSearchText()
            GENRES_LIBRARY -> getGenresWatchLaterFilms(genresList)
        }
    }

    companion object {
        const val ID = "Id"
        const val SEARCH = "Search"
        const val GENRES_LIBRARY = "GenresLibrary"
    }
}