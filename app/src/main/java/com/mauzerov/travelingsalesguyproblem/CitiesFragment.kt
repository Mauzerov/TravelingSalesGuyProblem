package com.mauzerov.travelingsalesguyproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {
    private lateinit var binding: FragmentCitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false)
        return binding.root
    }
}