package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val api: Api) : ViewModel() {

    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast>
        get() = _forecast

    // changed function name
    fun loadDataZip(zipCodeArg: String?) = runBlocking() {
        launch{ _forecast.value = api.getForecast(zipCodeArg!!) }
    }

    // new function for lat and lon
    fun loadDataLatLon(latArg: String?, lonArg: String?) = runBlocking {
        launch { _forecast.value = api.getForecastLatLon(latArg!!, lonArg!!) }
    }
}