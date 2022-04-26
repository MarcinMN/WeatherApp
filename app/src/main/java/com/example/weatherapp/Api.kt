package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c"
    ) : CurrentConditions

    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c",
        @Query("cnt") count: String = "16"
    ) : Forecast

    @GET("weather")
    suspend fun getCurrentConditionsLatLon(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c"
    ) : CurrentConditions

    @GET("forecast/daily")
    suspend fun getForecastLatLon(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c",
        @Query("cnt") count: String = "16"
    ) : Forecast
}