package com.example.knitting.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.CounterFragmentBinding

class CounterFragment : Fragment() {

    private lateinit var viewModel: CounterViewModel
    private lateinit var binding: CounterFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = CounterFragmentBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = CounterDatabase.getInstance(application).counterDAO
        val args = CounterFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory =  CounterViewModelFactory(dataSource, args.counterID )
        viewModel = ViewModelProvider(this, viewModelFactory).get(CounterViewModel::class.java)
        binding.counterViewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }
}