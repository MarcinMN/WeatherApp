package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentCurrentConditionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentConditionsFragment : Fragment() {
    private val apiKey = "5025177c6bd1ce93f4ffa221fd7f7c8c"

    private val args: CurrentConditionsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCurrentConditionsBinding
    private lateinit var viewModel: CurrentConditionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentConditionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Current Conditions"

        viewModel = CurrentConditionsViewModel()

        /*binding.forecastButton.setOnClickListener {
            val zipCodeArg = CurrentConditionsFragmentDirections.currentToForecast(
                args.zipCodeArg,
                null,
                null
            )
            Navigation.findNavController(it).navigate(zipCodeArg)
        } */

        // New ForecastButton Listener
        binding.forecastButton.setOnClickListener {
            if (args.zipCodeArg != null) {
                val zipCodeArg = CurrentConditionsFragmentDirections.currentToForecast(
                    args.zipCodeArg,
                    null,
                    null
                )
                Navigation.findNavController(it).navigate(zipCodeArg)
            } else {
                val latLonArg = CurrentConditionsFragmentDirections.currentToForecast(
                    null,
                    args.latArg,
                    args.lonArg
                )
                Navigation.findNavController(it).navigate(latLonArg)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.currentConditions.observe(this) { currentConditions ->
            bindData(currentConditions)
        }
        viewModel.loadData(args.currentConditionsArg!!)         // changed parameter
    }

    private fun bindData(currentConditions: CurrentConditions) {
        binding.cityName.text = currentConditions.name
        binding.temperature.text = getString(R.string.temperature, currentConditions.main.temp.toInt())
        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(binding.conditionIcon)
        binding.feelsLike.text = getString(R.string.feels_like, currentConditions.main.feelsLike.toInt())
        binding.lowTemp.text = getString(R.string.low_temp, currentConditions.main.tempMin.toInt())
        binding.highTemp.text = getString(R.string.high_temp, currentConditions.main.tempMax.toInt())
        binding.humidity.text = getString(R.string.humidity, currentConditions.main.humidity.toInt())
        binding.pressure.text = getString(R.string.pressure, currentConditions.main.pressure.toInt())
    }
}
