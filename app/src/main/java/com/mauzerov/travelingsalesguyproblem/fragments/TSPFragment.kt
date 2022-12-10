package com.mauzerov.travelingsalesguyproblem.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import com.mauzerov.travelingsalesguyproblem.MainActivityViewModel
import com.mauzerov.travelingsalesguyproblem.R
import com.mauzerov.travelingsalesguyproblem.databinding.FragmentTspBinding
import com.mauzerov.travelingsalesguyproblem.util.ObservableList
import com.mauzerov.travelingsalesguyproblem.util.saveViewAsImage

class TSPFragment : Fragment() {
    private lateinit var binding: FragmentTspBinding
    private lateinit var sharedViewModel: MainActivityViewModel

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tsp, container, false)

        sharedViewModel.graphObservable.observe(viewLifecycleOwner) {
            binding.fragmentTspStartCity.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.select_dialog_item,
                it.getNodes() + listOf("(Shortest)"))
            sharedViewModel.tspStartCityPosition = it.getNodes().size
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
        binding.saveResultBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                        requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {

                requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                saveViewAsImage(binding.root, "tsp.jpg", requireContext())
            }
        }

        binding.sharedVm = sharedViewModel
        binding.self = this
        return binding.root
    }

    companion object {
        fun newInstance(sharedViewModel: MainActivityViewModel) : TSPFragment {
            return TSPFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("sharedViewModel", sharedViewModel)
                }
            }
        }
    }
}