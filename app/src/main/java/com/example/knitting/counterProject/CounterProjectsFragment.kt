package com.example.knitting.counterProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.knitting.CounterAdapter
import com.example.knitting.CounterListener
import com.example.knitting.SwipeToDeleteCallback
import com.example.knitting.database.CounterDatabase
import com.example.knitting.databinding.CounterProjectsFragmentBinding

class CounterProjectsFragment : Fragment() {

    private lateinit var viewModel: CounterProjectsViewModel
    lateinit var binding: CounterProjectsFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = CounterProjectsFragmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = CounterDatabase.getInstance(application).counterDAO

        val viewModelFactory = CounterProjectsViewModelFactory(dataSource)
        val counterProjectsViewModel = ViewModelProvider(this, viewModelFactory).get(CounterProjectsViewModel::class.java)

        val adapter = CounterAdapter(CounterListener { counterID ->
            Toast.makeText(context, "$counterID", Toast.LENGTH_SHORT).show()
        })

        binding.projectsList.adapter = adapter
        binding.counterProjectsViewModel = counterProjectsViewModel
        binding.lifecycleOwner = this


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                counterProjectsViewModel.deleteCounter(adapter.getCounterAtPosition(pos))
                adapter.notifyItemRemoved(pos)
                adapter.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.projectsList)


        counterProjectsViewModel.counters.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        counterProjectsViewModel.navigateToSettingFragment.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(CounterProjectsFragmentDirections.actionCounterProjectsFragmentToSettingsFragment(it.counterID))
                counterProjectsViewModel.doneNavigatingToSettingFragment()
            }
        })

        return binding.root
    }

}