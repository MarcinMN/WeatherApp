package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    @Inject lateinit var searchViewModel: SearchViewModel

    private var lat: String? = null
    private var lon: String? = null

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
        // New location code starts here:
        binding.localWeatherButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission((activity as MainActivity), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                sendLocationData()
            } else {
                (activity as MainActivity).requestLocationPermission()
            }
        }
    }

    fun sendLocationData() {
        lat = (activity as MainActivity).getLat()
        lon = (activity as MainActivity).getLon()
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
    }
}