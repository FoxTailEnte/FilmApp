package com.example.cinematicapp.repository.data

import com.example.cinematicapp.R
import com.example.cinematicapp.presentation.adapters.allFilterItems.AllFilterItemsViewModel
import com.example.cinematicapp.presentation.adapters.filterRc.FilterRvViewModel
import com.example.cinematicapp.presentation.adapters.mainRcView.MainRcViewModel
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

fun mainRcViewListSubmit() = listOf(
    MainRcViewModel(
        R.string.all
    ), MainRcViewModel(
        R.string.shutter,
    ), MainRcViewModel(
        R.string.horror
    ), MainRcViewModel(
        R.string.detective
    ), MainRcViewModel(
        R.string.thriller
    ), MainRcViewModel(
        R.string.fantasy
    ), MainRcViewModel(
        R.string.comedies
    ), MainRcViewModel(
        R.string.journey
    ), MainRcViewModel(
        R.string.dramas
    ), MainRcViewModel(
        R.string.fantastic
    )
)
fun filterRcViewListSubmit() = listOf(
    FilterRvViewModel(
        R.string.filter_genres
    ), FilterRvViewModel(
        R.string.filter_years,
    ), FilterRvViewModel(
        R.string.filter_raiting
    ), FilterRvViewModel(
        R.string.filter_country
    )
)

fun allFilterItemsViewListSubmit() = listOf(
    AllFilterItemsViewModel(
        R.string.filter_dram,
        R.string.filter_2023,
        R.string.filter_rating_9,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_mel_dram,
        R.string.filter_2022,
        R.string.filter_rating_8,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_detective,
        R.string.filter_2021,
        R.string.filter_rating_7,
        R.string.filter_w
    ), AllFilterItemsViewModel(
        R.string.filter_criminal,
        R.string.filter_2020,
        R.string.filter_rating_6,
        R.string.filter_e
    ), AllFilterItemsViewModel(
        R.string.filter_rus,
        R.string.filter_2019,
        R.string.filter_rating_5,
        R.string.filter_r
    ), AllFilterItemsViewModel(
        R.string.filter_thriller,
        R.string.filter_2018,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_foreign,
        R.string.filter_2021_2023,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_fantasy,
        R.string.filter_2020_2023,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_comedy,
        R.string.filter_2019_2023,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_medical,
        R.string.filter_2015_2023,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_fighters,
        R.string.filter_2010_2015,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_history,
        R.string.filter_2000_2015,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_adventure,
        R.string.filter_2000_2010,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_horror,
        R.string.filter_1990_2000,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_fantastic,
        R.string.filter_1980_1990,
        null,
        R.string.filter_q
    ), AllFilterItemsViewModel(
        R.string.filter_army,
        R.string.filter_1980,
        null,
        R.string.filter_q
    )
)