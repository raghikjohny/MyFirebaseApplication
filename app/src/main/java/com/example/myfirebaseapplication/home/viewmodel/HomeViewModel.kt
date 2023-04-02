package com.example.myfirebaseapplication.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebaseapplication.common.AppConstants
import com.example.myfirebaseapplication.model.ApiResponse
import com.example.myfirebaseapplication.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    lateinit var api_response: MutableLiveData<ApiResponse>
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
   // val API_KEY: String = "f03d52b5fe8f522e99826ff1e5a990e6"
    fun searchWeather(city: String, apiClient: ApiInterface) {
        api_response = MutableLiveData<ApiResponse>()
        loading.postValue(true)
        error.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiClient.weatherResponse(city,AppConstants.APP_KEY)
            if (response.isSuccessful && response.body() != null) {
                api_response.postValue(response.body())
            } else {
                onError()
            }
        }
    }

    private fun onError() {
        error.postValue(true)
        loading.postValue(false)
    }
}