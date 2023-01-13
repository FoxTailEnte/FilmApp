package com.example.cinematicapp.presentation.adapters.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProfileModel(
    @StringRes
    var name: Int,
    @DrawableRes
    var icon: Int,
    var id: Int = 0
)

