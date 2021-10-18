package com.example.casestudy.ui.serviceDetailPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.model.detailPageModel.ServiceDetailModel
import com.example.casestudy.repository.AppRepository
import com.example.casestudy.util.StateResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ServiceDetailPageViewModel
constructor(private val repository: AppRepository): ViewModel() {

    private val _detailPageData = MutableStateFlow<StateResource<ServiceDetailModel>>(StateResource.Loading())
    private val detailPageData : StateFlow<StateResource<ServiceDetailModel>>
        get() = _detailPageData

    fun getData(serviceId: Int): StateFlow<StateResource<ServiceDetailModel>> {

        _detailPageData.value = StateResource.Loading()

        viewModelScope.launch {
            try {
                val response = repository.getServiceDetail(serviceId)
                _detailPageData.value = StateResource.Success(response)
            }
            catch (e: Exception) {
                _detailPageData.value = StateResource.Error(e)
            }
        }
        return detailPageData
    }

}