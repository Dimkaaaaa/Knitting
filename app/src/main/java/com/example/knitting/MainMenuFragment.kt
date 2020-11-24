package com.example.knitting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.knitting.databinding.MainMenuFragmentBinding

class MainMenuFragment : Fragment() {
    private lateinit var viewModel: MainMenuViewModel
    lateinit var binding: MainMenuFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = MainMenuFragmentBinding.inflate(inflater)

        binding.buttonCounter.setOnClickListener {
            this.findNavController().navigate(MainMenuFragmentDirections.actionMainMenuFragmentToCounterProjectsFragment())
        }

        return binding.root
    }
}