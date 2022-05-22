package com.example.weatherapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastCondition(
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
