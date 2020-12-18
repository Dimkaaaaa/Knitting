package com.example.knitting.dialog


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.DialogFragmentBinding


class MyDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentBinding
    lateinit var viewModel: MyDialogViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogFragmentBinding.inflate(inflater, null, false)
            builder.setView(binding.root)

            val application = requireNotNull(this.activity).application
            val dataSource = CounterDatabase.getInstance(application).counterDAO

            val viewModelFactory = MyDialogViewModelFactory(dataSource)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MyDialogViewModel::class.java)
            binding.lifecycleOwner = this
            binding.myDialogViewModel = viewModel

            viewModel.navigateToCounterProject.observe(this.requireActivity(), Observer {
                it?.let {
                    this.dialog?.cancel()
                    viewModel.doneNavigating()
                }
            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}