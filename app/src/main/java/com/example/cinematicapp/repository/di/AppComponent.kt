package com.example.cinematicapp.repository.di

import android.app.Application
import com.example.cinematicapp.presentation.ui.autorization.login.LogInPresenter
import com.example.cinematicapp.presentation.ui.registration.code.RegistrationCodePresenter
import com.example.cinematicapp.presentation.ui.registration.number.RegistrationNumberPresenter
import com.example.cinematicapp.repository.di.modules.FireBaseModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [FireBaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun provideLoginPresenter(): LogInPresenter
    fun provideRegistrationNumberPresenter(): RegistrationNumberPresenter
    fun provideRegistrationCodePresenter(): RegistrationCodePresenter

}