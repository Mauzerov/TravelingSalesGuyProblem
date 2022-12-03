package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mauzerov.travelingsalesguyproblem.algorithms.travelingSalesMan
import com.mauzerov.travelingsalesguyproblem.graph.AutoGeneratingGraph
import com.mauzerov.travelingsalesguyproblem.graph.Graph
import com.mauzerov.travelingsalesguyproblem.util.ObservableList

class MainActivityViewModel : BaseObservable() {
    val graphObservable = MutableLiveData(
            AutoGeneratingGraph<String>()
    ).apply {
        value?.addObserver { postValue(value) }
    }

    val graph: Graph<String>
        get() = graphObservable.value!!

    var bestPath : ObservableList<String> = ObservableList(mutableListOf())
    var distance: Int? = null

    fun findBestPath() {
        val result = graph.travelingSalesMan()
        bestPath.clear()
        bestPath.addAll(result.path)
        distance = result.distance
    }
    init {
        graph.addNode("ABC")
        graph.addNode("ADC")
    }
}