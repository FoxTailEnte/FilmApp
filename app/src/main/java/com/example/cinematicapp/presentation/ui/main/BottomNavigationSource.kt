package com.example.cinematicapp.presentation.ui.main

interface MainSource {

    fun hideSearchMenu(visibility: Boolean)
    fun hideBottomMenu(visibility: Boolean)
    fun searchListener(): Array<String>
}