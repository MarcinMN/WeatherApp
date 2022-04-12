package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val apiKey = "5025177c6bd1ce93f4ffa221fd7f7c8c"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}