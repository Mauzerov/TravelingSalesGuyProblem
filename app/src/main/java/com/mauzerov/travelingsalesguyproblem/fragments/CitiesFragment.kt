package com.mauzerov.travelingsalesguyproblem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.mauzerov.travelingsalesguyproblem.MainActivityViewModel
import com.mauzerov.travelingsalesguyproblem.R
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentCitiesBinding

class CitiesFragment(private val sharedViewModel: MainActivityViewModel) : Fragment() {
    private lateinit var binding: FragmentCitiesBinding
    var cityName : String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false)
        binding.sharedVm = sharedViewModel
        binding.self = this

        sharedViewModel.graphObservable.observe(viewLifecycleOwner) { graph ->
            binding.citiesFragmentAllCities.adapter = object :
                ArrayAdapter<String>(
                    requireContext(),
                    R.layout.text_button_list_item,
                    graph.getNodes()
                ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    if (convertView != null)
                        return convertView
                    val view = LayoutInflater.from(requireContext())
                        .inflate(R.layout.text_button_list_item, null)
                    view.findViewById<ImageButton>(R.id.text_button_list_button)
                        .setOnClickListener {
                            graph.removeNode(graph.getNodes()[position])
                        }
                    view.findViewById<TextView>(R.id.text_button_list_text).text =
                        graph.getNodes()[position]
                    return view
                }
            }
        }
        return binding.root
    }

    private val mCallbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = mCallbacks.add(callback)
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = mCallbacks.remove(callback)

    private fun notifyPropertyChanged(fieldId: Int) {
        mCallbacks.notifyCallbacks(this, fieldId, null)
    }
}