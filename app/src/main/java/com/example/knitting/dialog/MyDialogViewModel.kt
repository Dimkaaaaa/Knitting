package com.example.knitting.dialog

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDialogViewModel(val database: CounterDAO) : ViewModel() {

    private var _visibility = MutableLiveData<Int>()
    val visibility: LiveData<Int>
        get() = _visibility

    val projectNameText = MutableLiveData<String>()

    private var _navigateToCounterProject = MutableLiveData<Boolean>()
    val navigateToCounterProject: LiveData<Boolean>
        get() = _navigateToCounterProject


    init {
        _visibility.value = View.GONE
        projectNameText.value = ""
    }

    fun doneNavigating(){
        _navigateToCounterProject.value = null
    }


    private fun onInsertNewCounter() {
        viewModelScope.launch{
            Dispatchers.IO
            val counter = Counter()
            counter.counterName = projectNameText.value.toString()
            database.insert(counter)
        }
    }

    fun onOk() {
        if (projectNameText.value.toString().isEmpty())
            _visibility.value = View.VISIBLE
        else
            onInsertNewCounter()
            _navigateToCounterProject.value = true
    }

    fun onCancel() {
        _navigateToCounterProject.value = true
    }
}