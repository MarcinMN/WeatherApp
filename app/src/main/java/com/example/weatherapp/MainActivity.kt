package com.example.weatherapp

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val apiKey = "5025177c6bd1ce93f4ffa221fd7f7c8c"
    private val REQUEST_LOCATION_PERMISSION = 1234
    //private var lat: String? = "44.9537"
    //private var lon: String? = "-93.0900"
    private var lat: String? = null
    private var lon: String? = null

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
        locationRequest.interval = 0L
        locationRequest.fastestInterval = 0L
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.forEach {
                    lat = it.latitude.toString()
                    lon = it.longitude.toString()
                }
            }
        }
        getLastLocation()
    }

    override fun onResume() {
        super.onResume()
        getLastLocation()
    }

    fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            showLocationPermissionRationale()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun showLocationPermissionRationale() {
        AlertDialog.Builder(this)
            .setMessage(R.string.location_permission_rationale)
            .setNeutralButton(R.string.ok) { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_LOCATION_PERMISSION
                )
            }
            .create()
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun getLastLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 0L
        locationRequest.fastestInterval = 0L
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper()!!)

            fusedLocationClient.lastLocation.addOnSuccessListener(this) { task ->
                val location : Location? = task

                if (location != null) {
                    lat = location.latitude.toString()
                    lon = location.longitude.toString()
                } else {
                    // location is null!
                }
            }
        }
    }

    fun getLat() : String? {
        return lat
    }

    fun getLon() : String? {
        return lon
    }
}