package com.example.knitting.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        binding.imageButtonPause.visibility = View.INVISIBLE

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (viewModel.timerState.value as Boolean) viewModel.onPauseClick()
            viewModel.saveState()
            findNavController().navigate(CounterFragmentDirections.actionCounterFragmentToCounterProjectsFragment())
        }
        callback.isEnabled

        binding.multiAutoCompleteTextViewNote.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                v.hideKeyboard()
                v.clearFocus()
            }
        }
        binding.editTextCounterStep.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                v.hideKeyboard()
                v.clearFocus()
            }
        }

        viewModel.counter.observe(viewLifecycleOwner, Observer {
            if (it.countNumber.toInt() != 0 && it.state.isEmpty())
                viewModel.onStateChange()
        })


        viewModel.timerState.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.chronometerTime.base =
                    SystemClock.elapsedRealtime() + viewModel.time.value!!
                binding.chronometerTime.start()
                binding.imageButtonPlay.visibility = View.INVISIBLE
                binding.imageButtonPause.visibility = View.VISIBLE
            }
            if (!it) {
                binding.chronometerTime.stop()
                binding.imageButtonPlay.visibility = View.VISIBLE
                binding.imageButtonPause.visibility = View.INVISIBLE
            }
        })

        binding.imageButtonResetTimer.setOnClickListener {
            viewModel.onResetTimerClick()
            binding.chronometerTime.stop()
            binding.chronometerTime.base = SystemClock.elapsedRealtime() + viewModel.time.value!!
            binding.imageButtonPlay.visibility = View.VISIBLE
            binding.imageButtonPause.visibility = View.INVISIBLE
        }

        return binding.root
    }

    override fun onPause() {
        if (viewModel.timerState.value as Boolean) viewModel.onPauseClick()
        viewModel.saveState()
        super.onPause()
    }

}