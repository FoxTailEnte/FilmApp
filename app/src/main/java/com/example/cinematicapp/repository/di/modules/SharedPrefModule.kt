package com.example.cinematicapp.repository.di.modules

import android.app.Application
import android.content.Context
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCase
import com.example.cinematicapp.domain.sharedPrefUseCase.SharedPrefUseCaseImpl
import com.example.cinematicapp.repository.sharedPref.SaveUserAuthStatus
import com.example.cinematicapp.repository.sharedPref.SaveUserAuthStatusImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Provides
    fun provideSharedPrefUseCase(saveUserAuthStatus: SaveUserAuthStatus): SharedPrefUseCase = SharedPrefUseCaseImpl(saveUserAuthStatus)

    @Provides
    fun provideSharedPref(context: Context): SaveUserAuthStatus = SaveUserAuthStatusImpl(context)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}