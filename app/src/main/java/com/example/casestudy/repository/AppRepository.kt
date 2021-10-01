package com.example.casestudy.repository

import com.example.casestudy.api.ApiService

class AppRepository (private val apiService: ApiService) {

    suspend fun getHomePage() = apiService.getHomePage()

    suspend fun getServiceDetail(serviceId: Int) = apiService.getServiceDetail(serviceId)

}