package com.example.knitting.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.QuestionDialogFragmentBinding

class QuestionDialog(val counter: Counter) : DialogFragment() {

    lateinit var binding: QuestionDialogFragmentBinding
    private lateinit var viewModel: QuestionDialogViewModel


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = QuestionDialogFragmentBinding.inflate(inflater, null, false)
            builder.setView(binding.root)

            val application = requireNotNull(this.activity).application
            val dataSource = CounterDatabase.getInstance(application).counterDAO

            val viewModelFactory = QuestionDialogViewModelFactory(dataSource, counter)
            viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionDialogViewModel::class.java)
            binding.lifecycleOwner = this
            binding.questionViewModel = viewModel


            viewModel.navigateToCounter.observe(this.requireActivity(), Observer {navigation ->
                navigation?.let {
                    this.dialog?.cancel()
                    viewModel.doneNavigating()
                }
            })


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}