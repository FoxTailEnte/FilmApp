package com.example.cinematicapp.repository.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.cinematicapp.presentation.ui.main.BottomNavigationSource

object Extensions {
    fun Fragment.backPressFinishApp() = requireActivity().onBackPressedDispatcher
        .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })

    fun Fragment.getBottomNavigation() = requireActivity() as? BottomNavigationSource
    fun Fragment.navigateTo(direction: NavDirections) = findNavController().navigate(direction)
    fun Fragment.navigateTo(id: Int) = findNavController().navigate(id)
    fun Fragment.navigateBack() = findNavController().popBackStack()
}