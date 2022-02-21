package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c"
    ) : Call<CurrentConditions>

    @GET("forecast/daily")
    fun getForecast(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "5025177c6bd1ce93f4ffa221fd7f7c8c",
        @Query("cnt") count: String = "16"
    ) : Call<Forecast>
}