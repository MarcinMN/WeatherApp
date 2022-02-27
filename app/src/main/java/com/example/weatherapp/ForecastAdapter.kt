package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ForecastListItemBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastAdapter(var forecastList: List<DayForecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(val binding: ForecastListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ForecastListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val iconName = forecastList[position].weather.firstOrNull()?.icon
            val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
            Glide.with(holder.itemView.context)
                .load(iconUrl)
                .into(holder.binding.forecastConditionIcon)
            holder.binding.forecastDate.text = context.getString(R.string.forecast_date, calculateDate(forecastList[position].dt))
            holder.binding.forecastTemp.text = context.getString(R.string.forecast_temp, forecastList[position].temp.day.toInt().toString())
            holder.binding.forecastHigh.text = context.getString(R.string.forecast_high, forecastList[position].temp.max.toInt().toString())
            holder.binding.forecastLow.text = context.getString(R.string.forecast_low, forecastList[position].temp.min.toInt().toString())
            holder.binding.forecastSunrise.text = context.getString(R.string.forecast_sunrise, calculateTime(forecastList[position].sunrise))
            holder.binding.forecastSunset.text = context.getString(R.string.forecast_sunset, calculateTime(forecastList[position].sunset))
        }
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    private fun calculateDate(date_long: Long) : String {
        val instant = Instant.ofEpochSecond(date_long)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val date = formatter.format(dateTime)
        return date
    }

    private fun calculateTime(time_long: Long) : String {
        val instant = Instant.ofEpochSecond(time_long)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("hh:mma")
        val time = formatter.format(dateTime)
        return time
    }
}