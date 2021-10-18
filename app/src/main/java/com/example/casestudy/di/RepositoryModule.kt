package com.example.casestudy.di

import com.example.casestudy.api.ApiService
import com.example.casestudy.repository.AppRepository
import org.koin.dsl.module

object RepositoryModule {

    fun provideRepository(api: ApiService) : AppRepository {
        return AppRepository(api)
    }
    val repositoryModule = module {
        single {
            provideRepository(get())
        }
    }
}