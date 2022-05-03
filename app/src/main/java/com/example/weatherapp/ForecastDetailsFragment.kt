package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.bumptech.glide.Glide

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ComposeView(requireContext())
        view.apply {
            setContent {
                ScreenLayout()
            }
        }
        return view
    }

    @Composable
    fun ScreenLayout() {

        val forecastList : List<DayForecast> = args.forecastArg!!.list
        val position : Int = args.positionArg
        val iconName = forecastList[position].weather.firstOrNull()?.icon
        val weatherDescription = forecastList[position].weather.firstOrNull()?.description
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Icon", modifier = Modifier.padding(16.dp))
            Text(
                weatherDescription!!,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
            Row {
                Text(getString(R.string.forecast_details_temp, forecastList[position].temp.day.toInt().toString()),
                    fontSize = 72.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Column {
                    Text(
                        getString(
                            R.string.forecast_high,
                            forecastList[position].temp.max.toInt().toString()
                        ),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp, 38.dp, 16.dp, 8.dp)
                    )
                    Text(
                        getString(
                            R.string.forecast_low,
                            forecastList[position].temp.min.toInt().toString()
                        ),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)
                    )
                }
            }
            Text(
                getString(
                    R.string.humidity,
                    forecastList[position].humidity
                ),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)
            )
            Text(
                getString(
                    R.string.pressure,
                    forecastList[position].pressure.toInt()
                ),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)
            )
            Text(
                getString(
                    R.string.wind_speed,
                    forecastList[position].speed.toString()
                ),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)
            )
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewMessage() {
        ScreenLayout()
    }
}