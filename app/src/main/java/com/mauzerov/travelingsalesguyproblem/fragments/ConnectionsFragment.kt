package com.mauzerov.travelingsalesguyproblem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.fragment.app.Fragment
import com.mauzerov.travelingsalesguyproblem.BR
import com.mauzerov.travelingsalesguyproblem.MainActivityViewModel
import com.mauzerov.travelingsalesguyproblem.R
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentConnectionsBinding

class ConnectionsFragment(private val sharedViewModel: MainActivityViewModel) :
    Fragment(),
    Observable {
    private lateinit var binding: FragmentConnectionsBinding
    var toCity : Int = 0
    var fromCity : Int = 0

    @Bindable
    var distance: String = ""
        get() {
            return sharedViewModel.graph.getEdgeWeight(toCity, fromCity)?.toString() ?: ""
        }

        set(value) {
            if (value.isEmpty()) return
            field = value
            BR.distance
            sharedViewModel.graph.setEdgeWeight(toCity, fromCity, value.toInt())
        }
    fun updateDistance() {
        notifyPropertyChanged(BR._all)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_connections, container, false)
        binding.self = this
        binding.sharedVm = sharedViewModel

        sharedViewModel.graphObservable.observe(viewLifecycleOwner) { graph ->
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                graph.getNodes()
            ).let {
                binding.fromCitySpinner.adapter = it
                binding.toCitySpinner.adapter = it
            }
        }
        return binding.root
    }

    private val mCallbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = mCallbacks.add(callback)
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = mCallbacks.remove(callback)

    fun notifyPropertyChanged(fieldId: Int) {
        mCallbacks.notifyCallbacks(this, fieldId, null)
    }
}