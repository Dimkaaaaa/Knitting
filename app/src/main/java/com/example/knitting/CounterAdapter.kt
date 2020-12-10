package com.example.knitting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.knitting.database.Counter
import com.example.knitting.databinding.CounterItemBinding


class CounterAdapter : ListAdapter<Counter, MyHolder>(CounterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item)
    }


}


class MyHolder private constructor(val binding: CounterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Counter) {
        binding.textViewProjectName.text = item.counterName
        binding.textViewProjectTime.text = item.time.toString()
    }

    companion object {
        fun from(parent: ViewGroup): MyHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CounterItemBinding.inflate(inflater, parent, false)
            return MyHolder(binding)
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