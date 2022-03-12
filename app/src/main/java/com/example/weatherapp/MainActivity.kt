package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val apiKey = "5025177c6bd1ce93f4ffa221fd7f7c8c"

    private lateinit var binding: ActivityMainBinding
    //@Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, SearchFragment()).commit()
        } */
    }
}