package com.example.knitting.counter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import com.example.knitting.dialog.QuestionDialog
import kotlinx.coroutines.launch

class CounterViewModel(
    private val dataSource: CounterDAO,
    private val counterID: Int,
    private val fragmentManager: FragmentManager
) : ViewModel() {


    val database = dataSource
    private val _counter = MediatorLiveData<Counter>()
    val counter: LiveData<Counter>
            get() = _counter

    var step = MutableLiveData<String>()

    private var timerState = false

    init {
        _counter.addSource(database.get(counterID), _counter::setValue)
    }

    fun onPlusClick() {
        val newCounter = _counter.value?.let { increase(it) }
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
    }

    fun onMinusClick() {
        val newCounter = _counter.value?.let { decrease(it) }
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
    }

    fun onStateChange(){
        _counter.value?.let { QuestionDialog(it) }?.show(fragmentManager, "Question dialog")
    }

    fun onResetClick() {
        val newCounter = _counter.value
        newCounter?.step = 1
        newCounter?.countNumber = 0
        newCounter?.state = ""
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
    }

    fun onPlayPauseClick(){

    }

    private fun increase(counter: Counter): Counter {
        val a = counter.countNumber
        val b = step.value?.toLong()
        counter.countNumber = a + b!!
        counter.step = step.value?.toLong() ?: 0
        return counter
    }

    private fun decrease(counter: Counter): Counter {
        val a = counter.countNumber
        val b = step.value?.toLong()
        counter.countNumber = a - b!!
        counter.step = step.value?.toLong() ?: 0
        return counter
    }


}