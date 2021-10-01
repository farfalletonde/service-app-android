package com.example.casestudy.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.model.homePageModel.HomePageModel
import com.example.casestudy.repository.AppRepository
import com.example.casestudy.util.StateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
@Inject
constructor(private val repository: AppRepository): ViewModel() {

    private val _homePageData = MutableLiveData<StateResource<HomePageModel>>()
    private val homePageData : LiveData<StateResource<HomePageModel>>
        get() = _homePageData

    fun getData(): LiveData<StateResource<HomePageModel>> {
        viewModelScope.launch {
            _homePageData.postValue(StateResource.Loading())
            try {
                val response = repository.getHomePage()
                _homePageData.postValue(StateResource.Success(response))
            }
            catch (e: Exception) {
                _homePageData.postValue(StateResource.Error(e))
            }
        }
        return homePageData
    }
}