package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.presentation.ui.autorization.registration.code.RegistrationCodeFragment
import com.example.cinematicapp.presentation.ui.autorization.registration.number.RegistrationNumberFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentsModule {

    @Provides
    @Singleton
    fun provideRegistrationNumberFragment(): RegistrationNumberFragment = RegistrationNumberFragment()
    @Provides
    @Singleton
    fun provideRegistrationCodeFragment(): RegistrationCodeFragment = RegistrationCodeFragment()
}