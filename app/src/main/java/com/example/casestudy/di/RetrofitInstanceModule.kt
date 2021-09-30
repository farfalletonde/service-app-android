package com.example.casestudy.di

import com.example.casestudy.api.ApiService
import com.example.casestudy.constants.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstanceModule {

    @Singleton
    @Provides
    fun provideGson() : Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideApiService(gson: Gson) : ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}