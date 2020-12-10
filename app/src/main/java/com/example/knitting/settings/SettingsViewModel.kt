package com.example.knitting.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.CounterDAO
import kotlinx.coroutines.launch

class SettingsViewModel(private val counterIdKey: Int, val database: CounterDAO) : ViewModel() {


    val projectNameText = MutableLiveData<String>()
    val step = MutableLiveData<String>()
    val note = MutableLiveData<String>()

    private var _navigateToCounterProject = MutableLiveData<Boolean?>()
    val navigateToCounterProject: LiveData<Boolean?>
        get() = _navigateToCounterProject

    init {
        _navigateToCounterProject.value = false
    }

    fun doneNavigation() {
        _navigateToCounterProject.value = null
    }


    fun onSaveNewCounter() {
        viewModelScope.launch {
            var counter = database.get(counterIdKey)
            counter.counterName = projectNameText.value.toString()
            counter.step = step.value?.toInt() ?: 0
            counter.note = note.value.toString()
            database.update(counter)
            _navigateToCounterProject.value = true
        }

    }
}