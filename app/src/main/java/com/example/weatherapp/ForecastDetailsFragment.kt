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

class ForecastDetailsFragment : Fragment() {

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
}

@Composable
fun ScreenLayout() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("City", fontSize = 30.sp, modifier = Modifier.padding(16.dp))
        Row {
            Text("Icon", modifier = Modifier.padding(16.dp))
            Text("Description", modifier = Modifier.padding(16.dp))
        }
        Text("Temp", modifier = Modifier.padding(16.dp))
        Row {
            Text("High", modifier = Modifier.padding(16.dp))
            Text("Low", modifier = Modifier.padding(16.dp))
        }
        Row {
            Text("Humidity", modifier = Modifier.padding(16.dp))
            Text("Pressure", modifier = Modifier.padding(16.dp))
            Text("Wind", modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMessage() {
    ScreenLayout()
}