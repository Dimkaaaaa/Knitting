package com.example.knitting

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.knitting.database.Counter


@BindingAdapter("projectName")
fun TextView.projectName(item: Counter?) {
    item?.let {
        text = item.counterName
    }
}

@BindingAdapter("projectTime")
fun TextView.projectTime(item: Counter?) {
    item?.let {
        text = item.time.toString()
    }
}

@BindingAdapter("counterName")
fun TextView.counterName(item: Counter?) {
    item?.let {
        text = item.counterName
    }
}

@BindingAdapter("counterNumber")
fun TextView.counterNumber(item: Counter?) {
    item?.let {
        text = item.countNumber.toString()
    }
}

@BindingAdapter("counterState")
fun TextView.counterState(item: Counter?) {
    item?.let {
        text = item.state
    }
}


@BindingAdapter("counterStep")
fun EditText.setCounterStep(item: Counter?) {
    item?.let {
        setText(item.step.toString())
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = context!!.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}