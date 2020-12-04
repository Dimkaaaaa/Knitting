package com.example.knitting

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CounterAdapter: RecyclerView.Adapter<MyHolder>() {
    var data = listOf<String>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.counter_item, parent,false) as TextView
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int = data.size
}


class MyHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class MyList() {
    val myList = arrayListOf("aaaa","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg","bbbb","cccc","dddd","eeee","ffff","gggg")
}