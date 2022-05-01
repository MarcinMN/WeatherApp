package com.example.weatherapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayForecast(
    var dt: Long,
    var sunrise: Long,
    var sunset: Long,
    var temp: ForecastTemp,
    var pressure: Float,
    var humidity: Int,
    var speed: Float,
    var weather: List<ForecastCondition>
) : Parcelable
