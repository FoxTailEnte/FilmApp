package com.example.cinematicapp.presentation.ui.later

import com.example.cinematicapp.databinding.FragmentWatchLaterBinding
import com.example.cinematicapp.presentation.base.BaseFragment


class WatchLaterFragment : BaseFragment<FragmentWatchLaterBinding, WatchLaterView, WatchLaterPresenter>(), WatchLaterView {

    override fun initializeBinding() = FragmentWatchLaterBinding.inflate(layoutInflater)

    override fun setupUi() {
    }

    override fun setLoadingState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }
}