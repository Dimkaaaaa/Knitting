package com.example.knitting.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDAO

class CounterViewModelFactory(private val dataSource: CounterDAO, private val counterID: Int): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            return CounterViewModel(dataSource, counterID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}