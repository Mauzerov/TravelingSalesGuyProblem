package com.mauzerov.travelingsalesguyproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentTspBinding

class TSPFragment(private val sharedViewModel: MainActivityViewModel) : Fragment() {
    private lateinit var binding: FragmentTspBinding
    class Vm : BaseObservable(){
        @Bindable
        var text: String = ""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tsp, container, false)
        binding.fragmentTspStartCity.adapter = ArrayAdapter<String>(
            binding.fragmentTspStartCity.context,
            androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item,
            GlobalViewModel.cities
        )
        binding.sharedVm = sharedViewModel
        binding.self = Vm()
        return binding.root
    }
}