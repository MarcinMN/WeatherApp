package com.example.weatherapp

data class DayForecast(
    var dt: Long,
    var sunrise: Long,
    var sunset: Long,
    var temp: ForecastTemp,
    var pressure: Float,
    var humidity: Int,
    var weather: List<ForecastCondition>
)
