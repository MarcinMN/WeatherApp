package com.example.weatherapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //private var zipCode: String? = null                       // removed var
    private lateinit var binding: FragmentSearchBinding
    @Inject lateinit var searchViewModel: SearchViewModel

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

        //searchViewModel = SearchViewModel()                                                   // line removed

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

        /*binding.button.setOnClickListener {
            searchViewModel.submitButtonClicked()
            Navigation.findNavController(it).navigate(R.id.searchToCurrent)
        } */

        // new setOnClickListener for safe args                                                     // block commented
        /*binding.button.setOnClickListener {
            zipCode = searchViewModel.submitButtonClicked()
            val zipCodeArg = SearchFragmentDirections.searchToCurrent(zipCode)
            Navigation.findNavController(it).navigate(zipCodeArg)
        } */

        // new setOnClickListener for safe args: CurrentConditions                                  // block added
        binding.button.setOnClickListener {
            searchViewModel.submitButtonClicked()
            val currentConditionsArg = SearchFragmentDirections.searchToCurrent(searchViewModel.currentConditions.value, searchViewModel.returnZipCode())
            Navigation.findNavController(it).navigate(currentConditionsArg)
        }
    }
}