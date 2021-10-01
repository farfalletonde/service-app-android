package com.example.casestudy.ui.serviceDetailPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.model.detailPageModel.ServiceDetailModel
import com.example.casestudy.repository.AppRepository
import com.example.casestudy.util.StateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceDetailPageViewModel
@Inject
constructor(private val repository: AppRepository): ViewModel() {

    private val _detailPageData = MutableLiveData<StateResource<ServiceDetailModel>>()
    private val detailPageData : LiveData<StateResource<ServiceDetailModel>>
        get() = _detailPageData

    fun getData(serviceId: Int): LiveData<StateResource<ServiceDetailModel>> {
        viewModelScope.launch {
            _detailPageData.postValue(StateResource.Loading())
            try {
                val response = repository.getServiceDetail(serviceId)
                _detailPageData.postValue(StateResource.Success(response))
            }
            catch (e: Exception) {
                _detailPageData.postValue(StateResource.Error(e))
            }
        }
        return detailPageData
    }

}