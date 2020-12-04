package com.example.knitting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.knitting.databinding.CounterProjectsFragmentBinding

class CounterProjectsFragment : Fragment() {

    private lateinit var viewModel: CounterProjectsViewModel
    lateinit var binding: CounterProjectsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CounterProjectsFragmentBinding.inflate(inflater, container, false)

        binding.buttonNewProject.setOnClickListener {
            this.findNavController().navigate(CounterProjectsFragmentDirections.actionCounterProjectsFragmentToSettingsFragment())
        }

        val myData = MyList().myList
        val adapter = CounterAdapter()
        binding.projectsList.adapter = adapter

        adapter.data = myData

        return binding.root
    }

}