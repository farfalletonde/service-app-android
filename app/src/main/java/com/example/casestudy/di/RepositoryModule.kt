package com.example.casestudy.di

import com.example.casestudy.api.ApiService
import com.example.casestudy.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api: ApiService) : AppRepository {
        return AppRepository(api)
    }
}