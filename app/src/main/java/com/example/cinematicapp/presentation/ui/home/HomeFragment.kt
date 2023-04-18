package com.example.cinematicapp.presentation.ui.home

import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentHomeBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.HomeFilmAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmResponse
import com.example.cinematicapp.presentation.adapters.mainRcView.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter
    private lateinit var adapter: HomeFilmAdapter
    private lateinit var adapterMain: MainRcViewAdapter

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
        if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun setupListener() = with(binding) {
        edSearchText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                val array = arrayOf(edSearchText.text.toString())
                presenter.getRandomFilms(array)
                requireActivity().setKeyboardVisibility(false)
            }
            true
        }
    }

    override fun setupUi() {
        initRc()
        initRcMain()
        getRandomFilms()
        getMainActivityView()?.hideBottomMenu(true)
    }

    private fun initRc() {
        adapter = HomeFilmAdapter({
            navigateTo(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment(it.id))
        }) {
            setLoadingState(false)
        }
        binding.rcHome.adapter = adapter
        binding.rcHome.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun initRcMain() {
        adapterMain = MainRcViewAdapter {
            if(getString(it.name) != getString(R.string.all)) {
                setLoadingState(true)
                val array = arrayOf(getString(it.name).lowercase())
                presenter.getGenresFilms(array)
            } else {
                presenter.getRandomFilms(arrayOf(""))
            }
        }
        binding.recyclerViewMain.adapter = adapterMain
    }

    private fun getRandomFilms() {
        setLoadingState(true)
        presenter.getRandomFilms(arrayOf(""))
    }

    override fun submitList(items: BaseFilmResponse) {
        val itemList = items.docs
        adapter.setСomposedData(itemList!!)
    }

    override fun setLoadingState(isLoading: Boolean) {
        binding.lPBar.isVisible = isLoading
    }

}