package com.example.casestudy

import android.app.Application
import com.example.casestudy.di.RepositoryModule
import com.example.casestudy.di.RetrofitInstanceModule
import com.example.casestudy.di.ViewModelModule
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(RepositoryModule.repositoryModule,
                RetrofitInstanceModule.gsonModule,
                RetrofitInstanceModule.apiModule,
                ViewModelModule.homePageViewModelModule,
                ViewModelModule.serviceDetailPageViewModelModule)
        }
    }
}