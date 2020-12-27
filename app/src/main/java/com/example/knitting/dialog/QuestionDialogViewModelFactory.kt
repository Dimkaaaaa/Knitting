package com.example.knitting.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO

class QuestionDialogViewModelFactory (private val dataSource: CounterDAO, private val counter: Counter): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionDialogViewModel::class.java)) {
            return QuestionDialogViewModel(dataSource, counter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}