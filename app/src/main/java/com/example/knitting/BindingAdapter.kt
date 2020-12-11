package com.example.knitting

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.knitting.database.Counter

@BindingAdapter("projectName")
fun TextView.projectName(item:Counter?){
    item?.let {
        text = item.counterName
    }
}

@BindingAdapter("projectTime")
fun TextView.projectTime(item:Counter?){
    item?.let {
        text = item.time.toString()
    }
}