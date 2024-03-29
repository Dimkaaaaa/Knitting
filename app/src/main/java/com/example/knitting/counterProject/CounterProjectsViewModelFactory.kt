package com.example.knitting.counterProject

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDAO

class CounterProjectsViewModelFactory(private val dataSource: CounterDAO, private val fragmentManager: FragmentManager): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CounterProjectsViewModel::class.java)) {
                return CounterProjectsViewModel(dataSource, fragmentManager) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}