package com.example.knitting.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import kotlinx.coroutines.launch

class QuestionDialogViewModel(val dataSource: CounterDAO, val counter: Counter) : ViewModel() {
    private var _navigateToCounter = MutableLiveData<Boolean>()
    val navigateToCounter: LiveData<Boolean>
        get() = _navigateToCounter

    private val myCounter = counter


    fun onDoneButtonCLick(){
        myCounter.state = "Связано"
        viewModelScope.launch{
            dataSource.update(myCounter)
        }
        _navigateToCounter.value = true
    }


    fun onWillButtonCLick(){
        myCounter.state = "Вяжется"
        viewModelScope.launch{
            dataSource.update(myCounter)
        }
        _navigateToCounter.value = true
    }

    fun doneNavigating() {
        _navigateToCounter.value = null
    }
}