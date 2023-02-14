package com.example.cinematicapp.repository.di.modules

import android.app.Application
import android.content.Context
import com.example.cinematicapp.repository.data.sharedpref.SaveUserAuthStatus
import com.example.cinematicapp.repository.data.sharedpref.SaveUserAuthStatusImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Provides
    fun provideSharedPref(context: Context): SaveUserAuthStatus = SaveUserAuthStatusImpl(context)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}