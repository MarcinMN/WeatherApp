package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private val apiKey = "5025177c6bd1ce93f4ffa221fd7f7c8c"

    private lateinit var binding: ActivityForecastBinding
    @Inject lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvForecast.layoutManager = LinearLayoutManager(this@ForecastActivity)
    }

    override fun onResume() {
        super.onResume()
        viewModel.forecast.observe(this) { forecast ->
            binding.rvForecast.adapter = ForecastAdapter(forecast.list)
        }
        viewModel.loadData()
    }
}