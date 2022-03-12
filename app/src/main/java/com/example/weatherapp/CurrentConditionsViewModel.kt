package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CurrentConditionsViewModel /*@Inject constructor(private val api: Api)*/ : ViewModel() {     // Inject was commented

    private val _currentConditions = MutableLiveData<CurrentConditions>()
    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions

    /*fun loadData() = runBlocking {
        launch { _currentConditions.value = api.getCurrentConditions("55121") }
    } */

    // New loadData for safe args                                                       // This block was commented
    /*fun loadData(zipCodeArg: String?) = runBlocking {
        launch { _currentConditions.value = api.getCurrentConditions(zipCodeArg!!) }
    } */

    // New loadData for safe arg: CurrentConditions                                     // This block is new
    fun loadData(currentConditionsArg: CurrentConditions) {
        _currentConditions.value = currentConditionsArg
    }
}
