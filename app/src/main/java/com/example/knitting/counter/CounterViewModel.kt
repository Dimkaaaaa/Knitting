package com.example.knitting.counter

import android.os.SystemClock
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knitting.database.Counter
import com.example.knitting.database.CounterDAO
import com.example.knitting.dialog.QuestionDialog
import kotlinx.coroutines.launch

class CounterViewModel(
    val dataSource: CounterDAO,
    counterID: Int,
    private val fragmentManager: FragmentManager
) : ViewModel() {


    val database = dataSource

    private val _counter: LiveData<Counter> = dataSource.get(counterID)
    val counter: LiveData<Counter>
        get() = _counter

    private val currentCounter: Counter?
        get() = counter.value


    var step = MutableLiveData<String>()
    var note = MutableLiveData<String>()

    private var startTime = 0L
    private var finishTime = 0L

    private val _time = MutableLiveData<Long>()
    val time: LiveData<Long>
        get() = _time

    private val _timerState = MutableLiveData<Boolean>()
    val timerState: LiveData<Boolean>
        get() = _timerState


    init {
        _timerState.value = false
        _time.value = 0L
    }

    fun saveState() {
        currentCounter?.note = note.value.toString()
        currentCounter?.step = step.value?.toLong()!!
        viewModelScope.launch {
            currentCounter?.let { database.update(it) }
        }
    }

    fun onPlusClick() {
        currentCounter?.let { increase(it) }
        timeUpdate(currentCounter)
        viewModelScope.launch {
            currentCounter?.let { database.update(it) }
        }
    }

    fun onMinusClick() {
        currentCounter?.let { decrease(it) }
        timeUpdate(currentCounter)
        viewModelScope.launch {
            currentCounter?.let { database.update(it) }
        }
    }

    fun onStateChange() {
        currentCounter?.let { QuestionDialog(it) }?.show(fragmentManager, "Question dialog")
    }

    fun onResetClick() {
        currentCounter?.step = 1
        currentCounter?.countNumber = 0
        currentCounter?.state = ""
        timeUpdate(currentCounter)
        viewModelScope.launch {
            currentCounter?.let { database.update(it) }
        }
    }

    fun onPlayClick() {
        _time.value = currentCounter?.time
        startTime = SystemClock.elapsedRealtime()
        _timerState.value = true
    }

    fun onPauseClick() {
        _time.value = _time.value?.plus(startTime - SystemClock.elapsedRealtime())
        _timerState.value = false
        viewModelScope.launch {
            currentCounter?.time = _time.value!!
            currentCounter?.step = step.value?.toLong()!!
            currentCounter?.note = note.value.toString()
            currentCounter?.let { database.update(it) }
        }
    }


    fun onResetTimerClick() {
        _time.value = 0L
        startTime = 0L
        _timerState.value = false
        viewModelScope.launch {
            currentCounter?.time = 0L
            currentCounter?.step = step.value?.toLong()!!
            currentCounter?.note = note.value.toString()
            currentCounter?.let { database.update(it) }
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

    private fun timeUpdate(newCounter: Counter?) {
        if (_timerState.value == true) {
            _time.value = _time.value?.plus(startTime - SystemClock.elapsedRealtime())
            newCounter?.time = _time.value!!
            startTime = SystemClock.elapsedRealtime()
            newCounter?.note = note.value.toString()
        } else {
            onPlayClick()
            newCounter?.note = note.value.toString()
        }
    }
}
