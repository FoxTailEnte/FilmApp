package com.example.cinematicapp.presentation.ui.home

import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.databinding.FragmentHomeBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
          if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun setupUi() {
        getMainActivityView()?.hideSearchMenu(true)
        getMainActivityView()?.hideBottomMenu(true)
    }

}