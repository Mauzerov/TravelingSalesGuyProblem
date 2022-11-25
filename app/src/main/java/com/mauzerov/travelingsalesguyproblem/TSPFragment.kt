package com.mauzerov.travelingsalesguyproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentTspBinding

class TSPFragment : Fragment() {
    private lateinit var binding: FragmentTspBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tsp, container, false)
        binding.fragmentTspStartCity.setAdapter(ArrayAdapter<String>(
            binding.fragmentTspStartCity.context,
            androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item,
            GlobalViewModel.cities
        ))
        binding.`this` = this
        return binding.root
    }
}