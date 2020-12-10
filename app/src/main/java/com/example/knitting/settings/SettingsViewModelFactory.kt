package com.example.knitting.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.knitting.database.CounterDAO

class SettingsViewModelFactory(private val counterIdKey: Int, private val dataSource: CounterDAO ): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel( counterIdKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}