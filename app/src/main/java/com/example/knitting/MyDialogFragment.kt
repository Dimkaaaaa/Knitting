package com.example.knitting


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.DialogFragmentBinding


class MyDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogFragmentBinding.inflate(inflater, null, false)
            builder.setView(binding.root)

            val application = requireNotNull(this.activity).application
            val dataSource = CounterDatabase.getInstance(application).counterDAO



            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}