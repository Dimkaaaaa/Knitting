package com.example.knitting.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDAO

class MyDialogViewModelFactory(private val dataSource: CounterDAO): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyDialogViewModel::class.java)) {
            return MyDialogViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}