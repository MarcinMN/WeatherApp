package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()
    //private var forecast: Forecast = args.forecastArg!!
    //private var forecastList: List<DayForecast> = forecast.list
    //private var position: Int = args.positionArg

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ComposeView(requireContext())
        view.apply {
            setContent {
                //ScreenLayout(forecastList, position)
                ScreenLayout()
            }
        }
        return view
    }
/*
    @Composable
    fun ScreenLayout(forecastList : List<DayForecast>, position: Int) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text("City", fontSize = 30.sp, modifier = Modifier.padding(16.dp))
            Row {
                Text("Icon", modifier = Modifier.padding(16.dp))
                Text("Description", modifier = Modifier.padding(16.dp))
            }
            Text(forecastList[position].temp.day.toInt().toString(), modifier = Modifier.padding(16.dp))
            Row {
                Text(forecastList[position].temp.max.toInt().toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].temp.min.toInt().toString(), modifier = Modifier.padding(16.dp))
            }
            Row {
                Text(forecastList[position].humidity.toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].pressure.toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].speed.toString(), modifier = Modifier.padding(16.dp))
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewMessage() {
        ScreenLayout(forecastList, position)
    }  */

    @Composable
    fun ScreenLayout() {

        var forecastList : List<DayForecast> = args.forecastArg!!.list
        var position : Int = args.positionArg

        Column(modifier = Modifier.padding(16.dp)) {
            Text("City", fontSize = 30.sp, modifier = Modifier.padding(16.dp))
            Row {
                Text("Icon", modifier = Modifier.padding(16.dp))
                Text("Description", modifier = Modifier.padding(16.dp))
            }
            Text(forecastList[position].temp.day.toInt().toString(), modifier = Modifier.padding(16.dp))
            Row {
                Text(forecastList[position].temp.max.toInt().toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].temp.min.toInt().toString(), modifier = Modifier.padding(16.dp))
            }
            Row {
                Text(forecastList[position].humidity.toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].pressure.toString(), modifier = Modifier.padding(16.dp))
                Text(forecastList[position].speed.toString(), modifier = Modifier.padding(16.dp))
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewMessage() {
        ScreenLayout()
    }
}