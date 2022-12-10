package com.mauzerov.travelingsalesguyproblem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.mauzerov.travelingsalesguyproblem.BR
import com.mauzerov.travelingsalesguyproblem.MainActivityViewModel
import com.mauzerov.travelingsalesguyproblem.R
import com.mauzerov.travelingsalesguyproblem.RandomCityName
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentCitiesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class CitiesFragment : Fragment(), Observable {
    private lateinit var binding: FragmentCitiesBinding
    private lateinit var sharedViewModel: MainActivityViewModel
    @Bindable
    var cityName : String = ""

    fun addCity() {
        if (cityName.isEmpty())
            return Toast.makeText(requireContext(), "Miasto nie powinno być puste!", Toast.LENGTH_SHORT).show()
        sharedViewModel.graph.addNode(cityName)
        notifyPropertyChanged(BR._all)
    }

    fun getRandomCityName() {
        lifecycleScope.launch {
            RandomCityName().collect {
                cityName = it
                notifyPropertyChanged(BR.cityName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            sharedViewModel = it.get("sharedViewModel") as MainActivityViewModel
        }

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

   companion object {
       fun newInstance(sharedViewModel: MainActivityViewModel) : CitiesFragment {
           return CitiesFragment().apply {
                arguments = Bundle().apply {
                     putSerializable("sharedViewModel", sharedViewModel)
                }
           }
       }
   }
}