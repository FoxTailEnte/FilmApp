package com.example.cinematicapp.presentation.adapters.allFilterItems

data class CheckedItemModel(
    val mainFilter: String,
    val fullFilter: String,
    var checked: Boolean = false
)
