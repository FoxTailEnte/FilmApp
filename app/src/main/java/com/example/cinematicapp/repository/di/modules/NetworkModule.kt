package com.example.cinematicapp.repository.di.modules

import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCase
import com.example.cinematicapp.domain.apiUseCase.GetHomeFilmsUseCaseImpl
import com.example.cinematicapp.repository.network.api.Api
import com.example.cinematicapp.repository.network.api.GetHomeFilms
import com.example.cinematicapp.repository.network.api.GetHomeFilmsImpl
import dagger.Module
import dagger.Provides

@Module(
includes =[ApiModule::class]
)
class NetworkModule {

    @Provides
    fun provideHomeFilmUseCase(getHomeFilms: GetHomeFilms): GetHomeFilmsUseCase = GetHomeFilmsUseCaseImpl(getHomeFilms)

    @Provides
    fun provideHomeFilmApi(api: Api): GetHomeFilms = GetHomeFilmsImpl(api)
}