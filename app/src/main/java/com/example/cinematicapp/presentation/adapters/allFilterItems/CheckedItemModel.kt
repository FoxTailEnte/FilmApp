package com.example.cinematicapp.presentation.adapters.allFilterItems

data class CheckedItemModel(
    val filterPosition: Int,
    val itemPosition: Int,
    var checked: Boolean = false
)
