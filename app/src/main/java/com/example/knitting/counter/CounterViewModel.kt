package com.example.knitting.counter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import kotlinx.coroutines.launch

class CounterViewModel(private val dataSource: CounterDAO, val counterID: Int) : ViewModel() {


    val database = dataSource
    private val counter = MediatorLiveData<Counter>()
    fun getCounter() = counter

    var step = MutableLiveData<String>()


    init {
        counter.addSource(database.get(counterID), counter::setValue)
    }

    fun onPlusClick() {
        val newCounter = counter.value?.let { increase(it) }
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
    }

    fun onMinusClick() {
        val newCounter = counter.value?.let { decrease(it) }
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
    }

    fun onResetClick() {
        val newCounter = counter.value
        newCounter?.step = 1
        newCounter?.countNumber = 0
        viewModelScope.launch {
            if (newCounter != null) {
                database.update(newCounter)
            }
        }
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