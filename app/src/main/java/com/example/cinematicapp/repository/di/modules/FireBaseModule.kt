package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCase
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseDataUseCaseImpl
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCase
import com.example.cinematicapp.domain.firebaseUseCase.FireBaseSmsUseCaseImpl
import com.example.cinematicapp.repository.network.firebase.bd.FireBaseData
import com.example.cinematicapp.repository.network.firebase.bd.FireBaseDataImpl
import com.example.cinematicapp.repository.network.firebase.bd.FireBaseSms
import com.example.cinematicapp.repository.network.firebase.bd.FirebaseSmsImpl
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @Provides
    fun provideFireBaseSms(): FireBaseSms = FirebaseSmsImpl()
    @Provides
    fun provideFireBaseSmsUseCase(fireBaseSms: FireBaseSms): FireBaseSmsUseCase = FireBaseSmsUseCaseImpl(fireBaseSms)
    @Provides
    fun provideFireBaseData(): FireBaseData = FireBaseDataImpl()
    @Provides
    fun provideFireBaseDataUseCase(fireBaseData: FireBaseData): FireBaseDataUseCase = FireBaseDataUseCaseImpl(fireBaseData)
}