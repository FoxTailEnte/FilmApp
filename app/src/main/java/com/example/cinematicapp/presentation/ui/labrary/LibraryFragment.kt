package com.example.cinematicapp.presentation.ui.labrary

import com.example.cinematicapp.databinding.FragmentLibraryBinding
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView


class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    override fun initializeBinding() = FragmentLibraryBinding.inflate(layoutInflater)

    override fun setupUi() {
        getMainActivityView()?.hideSearchMenu(true)
    }
}