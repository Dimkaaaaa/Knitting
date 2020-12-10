package com.example.knitting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.knitting.database.Counter


class CounterAdapter : ListAdapter<Counter, MyHolder>(CounterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item)
    }


}


class MyHolder private constructor(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val counterProjectName: TextView = itemView.findViewById(R.id.textViewProjectName)
    val counterProjectTime: TextView = itemView.findViewById(R.id.textViewProjectTime)

    fun bind(item: Counter) {
        counterProjectName.text = item.counterName
        counterProjectTime.text = item.time.toString()
    }

    companion object {
        fun from(parent: ViewGroup): MyHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.counter_item, parent, false)
            return MyHolder(view)
        }
    }
}

class CounterDiffCallback : DiffUtil.ItemCallback<Counter>() {
    override fun areItemsTheSame(oldItem: Counter, newItem: Counter): Boolean {
        return oldItem.counterID == newItem.counterID
    }

    override fun areContentsTheSame(oldItem: Counter, newItem: Counter): Boolean {
        return oldItem == newItem
    }

}