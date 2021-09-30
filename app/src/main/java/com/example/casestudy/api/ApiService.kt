package com.example.casestudy.api

import com.example.casestudy.model.detailPageModel.ServiceDetailModel
import com.example.casestudy.model.homePageModel.HomePageModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("home")
    suspend fun getHomePage() : HomePageModel

    @GET("service/{serviceID}")
    suspend fun getServiceDetail(
        @Path("serviceID") serviceID : Int
    ) : ServiceDetailModel

}