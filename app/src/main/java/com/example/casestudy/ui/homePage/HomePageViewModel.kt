package com.example.casestudy.ui.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.model.homePageModel.HomePageModel
import com.example.casestudy.repository.AppRepository
import com.example.casestudy.util.StateResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomePageViewModel
constructor(private val repository: AppRepository): ViewModel() {


    private val _homePageData = MutableStateFlow<StateResource<HomePageModel>>(StateResource.Loading())
    private val homePageData: StateFlow<StateResource<HomePageModel>>
        get() = _homePageData

    fun getData(): StateFlow<StateResource<HomePageModel>> {

        _homePageData.value = StateResource.Loading()

        viewModelScope.launch {
            try {
                val response = repository.getHomePage()
                _homePageData.value = StateResource.Success(response)
            }
            catch (e: Exception) {
                _homePageData.value = StateResource.Error(e)
            }
        }
        return homePageData
    }
}