package com.example.cinematicapp.repository.data

import com.example.cinematicapp.R
import com.example.cinematicapp.presentation.adapters.profile.ProfileModel

fun profileListSubmit() = listOf(
    ProfileModel(
        R.string.profile_information,
        R.drawable.profile_information,
        1
    ), ProfileModel(
        R.string.profile_restore_pass,
        R.drawable.profile_restore_pass,
        1
    ), ProfileModel(
        R.string.profile_notification,
        R.drawable.profile_notification,
        1
    ), ProfileModel(
        R.string.profile_report,
        R.drawable.profile_report,
        1
    ), ProfileModel(
        R.string.profile_donation,
        R.drawable.profile_donation,
        1
    ), ProfileModel(
        R.string.profile_logout,
        R.drawable.profile_logout,
        1
    )
)