package com.example.casestudy.di

import com.example.casestudy.ui.homePage.HomePageViewModel
import com.example.casestudy.ui.serviceDetailPage.ServiceDetailPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val homePageViewModelModule = module {
        viewModel {
            HomePageViewModel(get())
        }
    }

    val serviceDetailPageViewModelModule = module {
        viewModel {
            ServiceDetailPageViewModel(get())
        }
    }
}