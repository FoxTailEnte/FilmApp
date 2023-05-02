package com.example.cinematicapp.presentation.ui.labrary

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.presentation.base.BasePresenter
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
    private var currentCall = ""
    private var currentFilmArray = arrayOf<String>()
    private var libraryList: HashMap<String, Int>? = null
    private lateinit var phone: String

    private fun getUserPhone(): String {
        phone = pref.getUserPhone()
        return phone
    }

    fun getLibraryList() {
        fireStore.getLibrary(getUserPhone()) { filmList ->
            libraryList = filmList
            val currentList:List<Int>? = filmList?.values?.toList()?.let { ArrayList(it) }
            if (currentList != null) {
                val array = currentList.map { it.toString() }
                getRandomLibraryFilms(array.toTypedArray(), Constants.ID)
            }
        }
    }

    fun getSearchList(name: String, type: String) {
        if(libraryList != null) {
            val searchList = libraryList!!.filter { it.key.lowercase().startsWith(name.lowercase()) }
            val idSearch: List<Int> = searchList.values.toList().let { ArrayList(it) }
            val array = idSearch.map { it.toString() }
             getRandomLibraryFilms(array.toTypedArray(), type)
        }
    }

    fun getRandomLibraryFilms(film: Array<String>, call: String) {
        currentFilmArray = film
        currentCall = call
        mDisposable.add(dataSource.getFilmsById(film, currentCall, film.size).subscribe {
            viewState.submitList(it)
        })
    }

    fun getGenresLibraryFilms(genres: Array<String>, call: String) {
        currentCall = call
        mDisposable.add(dataSource.getGenresLibraryFilms(currentFilmArray, genres, call, currentFilmArray.size).subscribe {
            viewState.submitList(it)
        })
    }


     fun getRefreshFilms() {
         when (currentCall) {
             Constants.BASE -> getRandomLibraryFilms(currentFilmArray,currentCall)
             //Constants.SEARCH -> getRandomFilms(currentFilmArray,currentCall)
             Constants.GENRES -> getGenresLibraryFilms(currentFilmArray,currentCall)
         }
     }
}