package com.mauzerov.travelingsalesguyproblem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.mauzerov.travelingsalesguyproblem.MainActivityViewModel
import com.mauzerov.travelingsalesguyproblem.R
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentTspBinding
import com.mauzerov.travelingsalesguyproblem.util.ObservableList

class TSPFragment(private val sharedViewModel: MainActivityViewModel) : Fragment() {
    private lateinit var binding: FragmentTspBinding
//    class Vm : BaseObservable(){
//        @Bindable
//    }
    var text: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tsp, container, false)

        sharedViewModel.citiesLiveData.observe(viewLifecycleOwner) {
            binding.fragmentTspStartCity.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it)
        }

        sharedViewModel.graphObservable.observe(viewLifecycleOwner) {
            binding.fragmentTspStartCity.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.select_dialog_item,
                it.getNodes() + listOf("(Shortest)"))
        }

        sharedViewModel.bestPath.addObserver { _, arg ->
            (arg as? ObservableList.Argument<*>)?.let { argument ->
                val (updateType, city) = argument
                binding.pathResult.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    sharedViewModel.bestPath
                )
            }
        }

        binding.sharedVm = sharedViewModel
        binding.self = this
        return binding.root
    }
}