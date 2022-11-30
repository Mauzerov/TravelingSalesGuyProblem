package com.mauzerov.travelingsalesguyproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
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

        sharedViewModel.graph.observe(viewLifecycleOwner) { graph ->
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
}