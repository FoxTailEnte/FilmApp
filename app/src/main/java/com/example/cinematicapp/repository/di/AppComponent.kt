package com.example.cinematicapp.repository.di

import android.app.Application
import com.example.cinematicapp.presentation.ui.autorization.forgotPassword.code.ForgotPasswordCodePresenter
import com.example.cinematicapp.presentation.ui.autorization.forgotPassword.number.ForgotPasswordPresenter
import com.example.cinematicapp.presentation.ui.autorization.forgotPassword.pass.ForgotPasswordNewPassPresenter
import com.example.cinematicapp.presentation.ui.autorization.login.LogInPresenter
import com.example.cinematicapp.presentation.ui.profile.ProfilePresenter
import com.example.cinematicapp.presentation.ui.profile.person.ProfilePersonPresenter
import com.example.cinematicapp.presentation.ui.registration.code.RegistrationCodePresenter
import com.example.cinematicapp.presentation.ui.registration.number.RegistrationNumberPresenter
import com.example.cinematicapp.presentation.ui.registration.person.RegistrationPersonInfoPresenter
import com.example.cinematicapp.repository.di.modules.FireBaseModule
import com.example.cinematicapp.repository.di.modules.SharedPrefModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FireBaseModule::class, SharedPrefModule::class])
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
    fun provideRegistrationPersonInfoPresenter(): RegistrationPersonInfoPresenter
    fun provideForgotPasswordNewPassPresenter(): ForgotPasswordNewPassPresenter
    fun provideForgotPasswordCodePresenter(): ForgotPasswordCodePresenter
    fun provideForgotPasswordPresenter(): ForgotPasswordPresenter
    fun provideProfilePersonPresenter(): ProfilePersonPresenter
    fun provideProfilePresenter(): ProfilePresenter
}