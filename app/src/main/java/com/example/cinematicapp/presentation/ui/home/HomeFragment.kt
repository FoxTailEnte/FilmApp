package com.example.cinematicapp.presentation.ui.home

import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.databinding.FragmentHomeBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.HomeFilmAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.ResponseModel
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter
    private lateinit var adapter: HomeFilmAdapter

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
        if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun setupUi() {
        initRc()
        presenter.getItems(getMainActivityView()?.searchListener())

        getMainActivityView()?.hideSearchMenu(true)
        getMainActivityView()?.hideBottomMenu(true)
    }

    private fun initRc() {
        adapter = HomeFilmAdapter()
        binding.rcHome.adapter = adapter
        binding.rcHome.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun submitList(items: ResponseModel) {
        adapter.submitList(items)
    }

}