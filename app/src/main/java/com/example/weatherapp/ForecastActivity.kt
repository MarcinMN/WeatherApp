package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        val rv_forecast = findViewById<RecyclerView>(R.id.rv_forecast)

        val forecastList = mutableListOf(
            DayForecast(1644732077, 1644758057, 1644795557, ForecastTemp(18.0f, 10.0f, 21.0f), 1029.0f, 74),
            DayForecast(1644887957, 1644844397, 1644882077, ForecastTemp(21.0f, 15.0f, 25.0f), 1019.0f, 71),
            DayForecast(1644934860, 1644930660, 1644968520, ForecastTemp(5.0f, 0.0f, 10.0f), 1027.0f, 68),
            DayForecast(1644991260, 1645017000, 1645054980, ForecastTemp(2.0f, -5.0f, 8.0f), 1023.0f, 64),
            DayForecast(1645077660, 1645103280, 1645141500, ForecastTemp(-3.0f, -9.0f, 5.0f), 1017.0f, 62),
            DayForecast(1645164060, 1645189620, 1645227960, ForecastTemp(16.0f, 11.0f, 22.0f), 1030.0f, 71),
            DayForecast(1645250460, 1645275900, 1645314480, ForecastTemp(25.0f, 22.0f, 29.0f), 1019.0f, 73),
            DayForecast(1645336860, 1645362180, 1645400940, ForecastTemp(28.0f, 24.0f, 32.0f), 1028.0f, 70),
            DayForecast(1645423260, 1645448520, 1645487400, ForecastTemp(35.0f, 30.0f, 40.0f), 1026.0f, 82),
            DayForecast(1645509660, 1645534800, 1645573920, ForecastTemp(34.0f, 31.0f, 36.0f), 1020.0f, 86),
            DayForecast(1645596060, 1645621140, 1645660380, ForecastTemp(21.0f, 18.0f, 24.0f), 1021.0f, 73),
            DayForecast(1645682460, 1645707420, 1645746900, ForecastTemp(19.0f, 17.0f, 21.0f), 1025.0f, 70),
            DayForecast(1645768860, 1645793700, 1645833360, ForecastTemp(14.0f, 12.0f, 17.0f), 1031.0f, 68),
            DayForecast(1645855260, 1645879980, 1645919820, ForecastTemp(21.0f, 16.0f, 26.0f), 1029.0f, 75),
            DayForecast(1645941660, 1645966320, 1646006340, ForecastTemp(25.0f, 22.0f, 28.0f), 1027.0f, 74),
            DayForecast(1646028060, 1646052600, 1646092800, ForecastTemp(27.0f, 23.0f, 30.0f), 1021.0f, 79)
        )

        val adapter = ForecastAdapter(forecastList)
        rv_forecast.adapter = adapter
        rv_forecast.layoutManager = LinearLayoutManager(this)

    }
}