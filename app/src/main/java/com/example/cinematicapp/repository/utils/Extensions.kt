package com.example.cinematicapp.repository.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder

import androidx.navigation.fragment.findNavController
import com.example.cinematicapp.presentation.ui.main.MainSource

object Extensions {
    fun Fragment.getMainActivityView() = requireActivity() as? MainSource
    fun Fragment.navigateTo(direction: NavDirections) = findNavController().navigate(direction)
    fun Fragment.navigateTo(id: Int) = findNavController().navigate(id)
    fun Fragment.navigateBack() = findNavController().navigateUp()
    fun Fragment.clearBackStack() = findNavController().backQueue.clear()

    fun Activity.setKeyboardVisibility(visibility: Boolean) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        if (visibility) {
            imm.showSoftInput(view, 0)
        } else {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun Int.getColor(context: Context): Int = ContextCompat.getColor(context, this)
}