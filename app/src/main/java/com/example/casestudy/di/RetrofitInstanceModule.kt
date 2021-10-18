package com.example.casestudy.di

import com.example.casestudy.api.ApiService
import com.example.casestudy.constants.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceModule {

    fun provideGson() : Gson {
        return GsonBuilder()
            .create()
    }

    fun provideApiService(gson: Gson) : ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    val gsonModule = module {
        single {
            provideGson()
        }
    }
    val apiModule = module {
        single {
            provideApiService(get())
        }
    }
}