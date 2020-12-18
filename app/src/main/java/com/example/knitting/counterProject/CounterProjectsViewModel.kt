package com.example.knitting.counterProject

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import com.example.knitting.dialog.MyDialogFragment
import kotlinx.coroutines.launch

class CounterProjectsViewModel(val database: CounterDAO, val fragmentManager: FragmentManager) :
    ViewModel() {

    var counters = database.getAllCounters()


    fun deleteCounter(counter: Counter) {
        viewModelScope.launch {
            database.delete(counter)
        }
    }

    private suspend fun insert(counter: Counter) {
        database.insert(counter)
    }


    fun onNewProjectClick() {
        val myDialog = MyDialogFragment()
        myDialog.show(fragmentManager, "My first dialog")
    }
}