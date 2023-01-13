package com.example.cinematicapp

import android.app.Application
import com.example.cinematicapp.repository.di.AppComponent
import com.example.cinematicapp.repository.di.DaggerAppComponent

class CinematicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
    companion object{
        lateinit var appComponent: AppComponent
    }
}