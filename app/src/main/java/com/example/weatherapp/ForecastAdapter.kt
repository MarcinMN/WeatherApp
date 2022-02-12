package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastAdapter(var forecastList: List<DayForecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var forecastConditionIcon = itemView.findViewById<ImageView>(R.id.forecast_condition_icon)
        var forecastDate = itemView.findViewById<TextView>(R.id.forecast_date)
        var forecastTemp = itemView.findViewById<TextView>(R.id.forecast_temp)
        var forecastHigh = itemView.findViewById<TextView>(R.id.forecast_high)
        var forecastLow = itemView.findViewById<TextView>(R.id.forecast_low)
        var forecastSunrise = itemView.findViewById<TextView>(R.id.forecast_sunrise)
        var forecastSunset = itemView.findViewById<TextView>(R.id.forecast_sunset)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_list_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.itemView.apply {
            holder.forecastDate.text = context.getString(R.string.forecast_date, calculateDate(forecastList[position].date))
            holder.forecastTemp.text = context.getString(R.string.forecast_temp, forecastList[position].temp.day.toInt().toString())
            holder.forecastHigh.text = context.getString(R.string.forecast_high, forecastList[position].temp.max.toInt().toString())
            holder.forecastLow.text = context.getString(R.string.forecast_low, forecastList[position].temp.min.toInt().toString())
            holder.forecastSunrise.text = context.getString(R.string.forecast_sunrise, calculateTime(forecastList[position].sunrise))
            holder.forecastSunset.text = context.getString(R.string.forecast_sunset, calculateTime(forecastList[position].sunset))
        }
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun calculateDate(date_long: Long) : String {
        val instant = Instant.ofEpochSecond(date_long)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val date = formatter.format(dateTime)
        return date
    }

    fun calculateTime(time_long: Long) : String {
        val instant = Instant.ofEpochSecond(time_long)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("hh:mma")
        val time = formatter.format(dateTime)
        return time
    }
}