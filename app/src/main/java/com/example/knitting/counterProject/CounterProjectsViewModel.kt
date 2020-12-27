package com.example.knitting.counterProject

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import com.example.knitting.dialog.MyDialogFragment
import kotlinx.coroutines.launch

class CounterProjectsViewModel(val database: CounterDAO, private val fragmentManager: FragmentManager) :
    ViewModel() {

    var counters = database.getAllCounters()
    private var _navigateToCounterFragment = MutableLiveData<Int>()
    val navigateToCounterFragment: LiveData<Int>
        get() = _navigateToCounterFragment

    fun onCounterClicked(counterID: Int){
        _navigateToCounterFragment.value = counterID
    }

    fun doneNavigating(){
        _navigateToCounterFragment.value = null
    }

    fun deleteCounter(counter: Counter) {
        viewModelScope.launch {
            database.delete(counter)
        }

    }

    fun onNewProjectClick() {
        val myDialog = MyDialogFragment()
        myDialog.show(fragmentManager, "My first dialog")
    }
}