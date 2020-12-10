package com.example.knitting.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
    lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = CounterDatabase.getInstance(application).counterDAO

        val arguments = SettingsFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = SettingsViewModelFactory(arguments.counterID, dataSource)
        val settingsFragmentViewModel = ViewModelProvider(this, viewModelFactory).get(
            SettingsViewModel::class.java)

        binding.settingsViewModel = settingsFragmentViewModel
        binding.lifecycleOwner = this

        settingsFragmentViewModel.navigateToCounterProject.observe(viewLifecycleOwner, Observer{
            if(it == true){
                this.findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToCounterProjectsFragment())
                settingsFragmentViewModel.doneNavigation()
            }
        })

        return binding.root
    }

}