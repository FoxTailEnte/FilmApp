package com.example.cinematicapp.presentation.adapters.allFilterItems

import androidx.annotation.StringRes

data class AllFilterItemsViewModel(
    @StringRes
    var genre: Int,
    var years: Int,
    var rating: Int?,
    var country: Int?,
    //var isChecked: Boolean = false
)
