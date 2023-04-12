package com.example.cinematicapp.presentation.ui.labrary

import com.example.cinematicapp.databinding.FragmentLibraryBinding
import com.example.cinematicapp.presentation.base.BaseFragment


class LibraryFragment : BaseFragment<FragmentLibraryBinding, LibraryView, LibraryPresenter>(), LibraryView {

    override fun initializeBinding() = FragmentLibraryBinding.inflate(layoutInflater)

    override fun setupUi() {
    }

    override fun setLoadingState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }
}