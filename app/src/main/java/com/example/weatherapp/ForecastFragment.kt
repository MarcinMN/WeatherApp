package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment(), OnItemClickListener {

    private val args: ForecastFragmentArgs by navArgs()
    private lateinit var binding: FragmentForecastBinding
    @Inject lateinit var forecastViewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Forecast"

        binding.rvForecast.layoutManager = LinearLayoutManager(context)

    }

    override fun onItemClicked(position: Int) {
        val forecastArg = ForecastFragmentDirections.forecastToForecastDetails(
            forecastViewModel.forecast.value,
            position
        )
        Navigation.findNavController(binding.root).navigate(forecastArg)
    }

    override fun onResume() {
        super.onResume()
        forecastViewModel.forecast.observe(this) { forecast ->
            binding.rvForecast.adapter = ForecastAdapter(forecast.list, this)
        }
        if (args.zipCodeArg != null) {
            forecastViewModel.loadDataZip(args.zipCodeArg)
        } else {
            forecastViewModel.loadDataLatLon(args.latArg, args.lonArg)
        }
    }
}