package com.example.weatherapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val CHANNEL_ID = "Notification Channel"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    @Inject lateinit var searchViewModel: SearchViewModel

    private var lat: String? = null
    private var lon: String? = null
    private var notificationActive: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Search"

        createNotificationChannel()

        searchViewModel.enableButton.observe(this) { enable ->
            binding.button.isEnabled = enable
        }

        searchViewModel.showErrorDialog.observe(this) { showError ->
            if (showError) {
                ErrorDialogFragment().show(childFragmentManager, ErrorDialogFragment.TAG)
            }
        }

        binding.zipCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.toString()?.let { searchViewModel.updateZipCode(it) }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.button.setOnClickListener {
            searchViewModel.submitButtonClicked()
            if(!(searchViewModel.showErrorDialog.value!!)) {
                val currentConditionsArg = SearchFragmentDirections.searchToCurrent(
                    searchViewModel.currentConditions.value,
                    searchViewModel.returnZipCode(),
                    null,
                    null
                )
                Navigation.findNavController(it).navigate(currentConditionsArg)
            } else {
                searchViewModel.resetErrorDialog()
            }
        }
        binding.localWeatherButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission((activity as MainActivity), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                sendLocationData()
            } else {
                (activity as MainActivity).requestLocationPermission()
            }
        }

        binding.notificationButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission((activity as MainActivity), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getLocation()
                if (!notificationActive) {
                    // turn on notifications
                    notificationActive = true
                    sendNotification()
                } else {
                    // turn off notifications
                    notificationActive = false
                    with(NotificationManagerCompat.from(requireContext())) {
                        cancel(1)
                    }

                }
            } else {
                (activity as MainActivity).requestLocationPermission()
            }
        }
    }

    private fun getLocation() {
        lat = (activity as MainActivity).getLat()
        lon = (activity as MainActivity).getLon()
        try {
            searchViewModel.notificationButtonClicked(lat!!, lon!!)
        } catch (e : NullPointerException) {
            lat = "21.3069"
            lon = "-157.8583"
            searchViewModel.notificationButtonClicked(lat!!, lon!!)
        }
    }

    private fun sendLocationData() {
        lat = (activity as MainActivity).getLat()
        lon = (activity as MainActivity).getLon()
        try {
            searchViewModel.localWeatherButtonClicked(lat!!, lon!!)
        } catch (e : NullPointerException) {
            lat = "21.3069"
            lon = "-157.8583"
            searchViewModel.localWeatherButtonClicked(lat!!, lon!!)
        }
        /*try {
            searchViewModel.localWeatherButtonClicked(lat!!, lon!!)
        } catch (e : NullPointerException) {
            // Catch block is for when the emulator doesn't have location yet. Default location: Honolulu, HI
            // Once the emulator wakes up and has a location, the "real" location is used.
            lat = "21.3069"
            lon = "-157.8583"
            searchViewModel.localWeatherButtonClicked(lat!!, lon!!)
            if(!(searchViewModel.showErrorDialog.value!!)) {
                val currentConditionsArg = SearchFragmentDirections.searchToCurrent(
                    searchViewModel.currentConditions.value,
                    null,
                    lat,
                    lon
                )
                Navigation.findNavController(binding.root).navigate(currentConditionsArg)
            } else {
                searchViewModel.resetErrorDialog()
            }
        } */
        if(!(searchViewModel.showErrorDialog.value!!)) {
            val currentConditionsArg = SearchFragmentDirections.searchToCurrent(
                searchViewModel.currentConditions.value,
                null,
                lat,
                lon
            )
            Navigation.findNavController(binding.root).navigate(currentConditionsArg)
        } else {
            searchViewModel.resetErrorDialog()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Channel Title"
            val descriptionText = "Notification Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        var builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.sun)
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireContext())) {
            notify(1, builder.build())
        }
    }
}