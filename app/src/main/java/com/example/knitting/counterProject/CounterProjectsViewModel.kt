package com.example.knitting.counterProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import kotlinx.coroutines.launch

class CounterProjectsViewModel(val database: CounterDAO) : ViewModel() {

    private var _navigateToSettingFragment = MutableLiveData<Counter>()
    val navigateToSettingFragment: LiveData<Counter>
        get() = _navigateToSettingFragment

    fun doneNavigatingToSettingFragment() {
        _navigateToSettingFragment.value = null
    }

    var counters = database.getAllCounters()

    private suspend fun insert(counter: Counter) {
        database.insert(counter)
    }

    fun onNewProjectClick() {
        viewModelScope.launch {
            var counter = Counter()
            insert(counter)
            _navigateToSettingFragment.value = database.getCounter()
        }
    }
}