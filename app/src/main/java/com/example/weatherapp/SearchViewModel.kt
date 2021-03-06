package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val api: Api) : ViewModel() {

    private var zipCode: String? = null
    private val _enableButton = MutableLiveData(false)
    private val _showErrorDialog = MutableLiveData(false)
    private val _currentConditions = MutableLiveData<CurrentConditions>()

    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions

    val enableButton: LiveData<Boolean>
        get() = _enableButton

    val showErrorDialog: LiveData<Boolean>
        get() = _showErrorDialog

    fun updateZipCode(zipCode: String) {
        if (zipCode != this.zipCode) {
            this.zipCode = zipCode
            _enableButton.value = isValidZipCode(zipCode)
        }
    }

    private fun isValidZipCode(zipCode: String): Boolean {
        return zipCode.length == 5 && zipCode.all { it.isDigit() }
    }

    fun submitButtonClicked() = runBlocking {
        launch {
            try {
                _currentConditions.value = api.getCurrentConditions(zipCode.toString())
            } catch(e : HttpException) {
                _showErrorDialog.value = true
            }
        }
    }

    fun localWeatherButtonClicked(lat: String, lon: String) = runBlocking {
        launch {
            try {
                _currentConditions.value = api.getCurrentConditionsLatLon(lat, lon)
            } catch(e : HttpException) {
                _showErrorDialog.value = true
            }
        }
    }

    fun notificationButtonClicked(lat: String, lon: String) = runBlocking {
        launch {
            try {
                _currentConditions.value = api.getCurrentConditionsLatLon(lat, lon)
            } catch(e : HttpException) {
                _showErrorDialog.value = true
            }
        }
    }

    fun returnZipCode() : String? {
        return zipCode
    }

    fun resetErrorDialog() {
        _showErrorDialog.value = false
    }
}