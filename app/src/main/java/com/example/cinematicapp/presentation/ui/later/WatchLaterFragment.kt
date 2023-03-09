package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.databinding.FragmentWatchLaterBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView


class WatchLaterFragment : BaseFragment<FragmentWatchLaterBinding, WatchLaterView, WatchLaterPresenter>(), WatchLaterView {

    override fun initializeBinding() = FragmentWatchLaterBinding.inflate(layoutInflater)

    override fun setupUi() {
        getMainActivityView()?.hideSearchMenu(true)
    }
}