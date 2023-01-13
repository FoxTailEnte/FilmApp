package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.repository.network.firebase.AuthFireBaseUseCase
import com.example.cinematicapp.repository.network.firebase.AuthFirebaseUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @Provides
    fun provideAuthFireBaseUseCase(): AuthFireBaseUseCase = AuthFirebaseUseCaseImpl()
}