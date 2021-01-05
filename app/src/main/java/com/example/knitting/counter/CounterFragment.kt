package com.example.knitting.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.CounterFragmentBinding
import com.example.knitting.hideKeyboard

class CounterFragment : Fragment() {

    private lateinit var viewModel: CounterViewModel
    private lateinit var binding: CounterFragmentBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CounterFragmentBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = CounterDatabase.getInstance(application).counterDAO
        val args = CounterFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory =
            CounterViewModelFactory(dataSource, args.counterID, childFragmentManager)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CounterViewModel::class.java)
        binding.counterViewModel = viewModel
        binding.lifecycleOwner = this

        binding.multiAutoCompleteTextViewNote.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                v.hideKeyboard()
                v.clearFocus()
            }
        }
        binding.editTextCounterStep.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                v.hideKeyboard()
                v.clearFocus()
            }
        }

        viewModel.counter.observe(viewLifecycleOwner, Observer {
            if (it.countNumber.toInt() != 0 && it.state.isEmpty())
                viewModel.onStateChange()
        })


        var chronometer = binding.chronometerTime

        binding.imageButtonPlayPause.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime() - 20000
            chronometer.start()
        }
        binding.imageButtonPause.setOnClickListener{
            chronometer.stop()
        }
        binding.imageButtonResetTimer.setOnClickListener{

            Toast.makeText(context,binding.chronometerTime.text,Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}