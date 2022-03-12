package com.example.weatherapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTemp(
    var day: Float,
    var min: Float,
    var max: Float
) : Parcelable
