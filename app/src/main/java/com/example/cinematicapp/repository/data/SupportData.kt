package com.example.cinematicapp.repository.data

import com.example.cinematicapp.R
import com.example.cinematicapp.presentation.adapters.allFilterItems.AllFilterItemsViewModel
import com.example.cinematicapp.presentation.adapters.filterRc.FilterRvViewModel
import com.example.cinematicapp.presentation.adapters.main.MainRcViewModel
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
        R.string.profile_logout,
        R.drawable.profile_logout,
        1
    )
)

fun mainRcViewListSubmit() = listOf(
    MainRcViewModel(
        R.string.all
    ), MainRcViewModel(
        R.string.shutter
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
    ), FilterRvViewModel(
        R.string.filter_button
    )
)

fun allFilterGenresListSubmit() = listOf(
    AllFilterItemsViewModel(
        R.string.filter_dram
    ), AllFilterItemsViewModel(
        R.string.filter_mel_dram
    ), AllFilterItemsViewModel(
        R.string.filter_detective
    ), AllFilterItemsViewModel(
        R.string.filter_criminal
    ), AllFilterItemsViewModel(
        R.string.filter_thriller
    ), AllFilterItemsViewModel(
        R.string.filter_fantasy
    ), AllFilterItemsViewModel(
        R.string.filter_comedy
    ), AllFilterItemsViewModel(
        R.string.filter_fighters
    ), AllFilterItemsViewModel(
        R.string.filter_history
    ), AllFilterItemsViewModel(
        R.string.filter_adventure
    ), AllFilterItemsViewModel(
        R.string.filter_horror
    ), AllFilterItemsViewModel(
        R.string.filter_fantastic
    ), AllFilterItemsViewModel(
        R.string.filter_army,
    )
)

fun allFilterYearsListSubmit() = listOf(
    AllFilterItemsViewModel(
        R.string.filter_2023
    ), AllFilterItemsViewModel(
        R.string.filter_2022
    ), AllFilterItemsViewModel(
        R.string.filter_2021
    ), AllFilterItemsViewModel(
        R.string.filter_2020
    ), AllFilterItemsViewModel(
        R.string.filter_2019
    ), AllFilterItemsViewModel(
        R.string.filter_2018
    ), AllFilterItemsViewModel(
        R.string.filter_2017
    ), AllFilterItemsViewModel(
        R.string.filter_2016
    ), AllFilterItemsViewModel(
        R.string.filter_2010_2015
    ), AllFilterItemsViewModel(
        R.string.filter_2005_2010
    ), AllFilterItemsViewModel(
        R.string.filter_2000_2005
    ), AllFilterItemsViewModel(
        R.string.filter_1990_2000
    ), AllFilterItemsViewModel(
        R.string.filter_1980_1990
    ), AllFilterItemsViewModel(
        R.string.filter_1980
    )
)

fun allFilterRatingListSubmit() = listOf(
    AllFilterItemsViewModel(
        R.string.filter_rating_9
    ), AllFilterItemsViewModel(
        R.string.filter_rating_8
    ), AllFilterItemsViewModel(
        R.string.filter_rating_7
    ), AllFilterItemsViewModel(
        R.string.filter_rating_6
    ), AllFilterItemsViewModel(
        R.string.filter_rating_5
    )
)

fun allFilterCountryListSubmit() = listOf(
    AllFilterItemsViewModel(
        R.string.filter_russia
    ), AllFilterItemsViewModel(
        R.string.filter_usa
    ), AllFilterItemsViewModel(
        R.string.filter_great_britain
    ), AllFilterItemsViewModel(
        R.string.filter_france
    ), AllFilterItemsViewModel(
        R.string.filter_ussr
    ), AllFilterItemsViewModel(
        R.string.filter_italy
    ), AllFilterItemsViewModel(
        R.string.filter_canada
    ), AllFilterItemsViewModel(
        R.string.filter_spain
    ), AllFilterItemsViewModel(
        R.string.filter_germany
    ), AllFilterItemsViewModel(
        R.string.filter_india
    ), AllFilterItemsViewModel(
        R.string.filter_denmark
    ), AllFilterItemsViewModel(
        R.string.filter_turkey
    ), AllFilterItemsViewModel(
        R.string.filter_south_korea
    ), AllFilterItemsViewModel(
        R.string.filter_australia
    ), AllFilterItemsViewModel(
        R.string.filter_sweden
    ), AllFilterItemsViewModel(
        R.string.filter_china
    ), AllFilterItemsViewModel(
        R.string.filter_argentina
    ), AllFilterItemsViewModel(
        R.string.filter_norway
    ), AllFilterItemsViewModel(
        R.string.filter_belgium
    ), AllFilterItemsViewModel(
        R.string.filter_japan
    ), AllFilterItemsViewModel(
        R.string.filter_kazakhstan
    ), AllFilterItemsViewModel(
        R.string.filter_poland
    ), AllFilterItemsViewModel(
        R.string.filter_ireland
    ), AllFilterItemsViewModel(
        R.string.filter_colombia
    ), AllFilterItemsViewModel(
        R.string.filter_mexico
    ), AllFilterItemsViewModel(
        R.string.filter_finland
    ), AllFilterItemsViewModel(
        R.string.filter_austria
    ), AllFilterItemsViewModel(
        R.string.filter_belarus
    ), AllFilterItemsViewModel(
        R.string.filter_bulgaria
    ), AllFilterItemsViewModel(
        R.string.filter_greece
    )
)