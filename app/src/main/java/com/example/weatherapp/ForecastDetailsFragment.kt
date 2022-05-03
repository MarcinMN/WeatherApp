package com.example.weatherapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Forecast Details"
    }

    @Composable
    fun ScreenLayout() {

        val forecastList : List<DayForecast> = args.forecastArg!!.list
        val position : Int = args.positionArg
        val iconName = forecastList[position].weather.firstOrNull()?.icon
        val weatherDescription = forecastList[position].weather.firstOrNull()?.description
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        val image = loadPicture(url = iconUrl, defaultImage = R.drawable.rectangle).value

        Column(modifier = Modifier.padding(16.dp)) {
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "weather condition icon",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                weatherDescription!!,
                fontSize = 18.sp,
                modifier = Modifier.padding(32.dp, 0.dp, 16.dp, 16.dp)
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
                    forecastList[position].speed.toInt().toString()
                ),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)
            )
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun loadPicture(url: String, @DrawableRes defaultImage: Int) : MutableState<Bitmap?> {
        val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(defaultImage)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmapState.value = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmapState.value = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
        return bitmapState
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewMessage() {
        ScreenLayout()
    }
}