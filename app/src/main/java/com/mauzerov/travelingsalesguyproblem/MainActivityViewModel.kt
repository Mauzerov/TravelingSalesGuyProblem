package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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
        value?.addObserver {
            postValue(value)
            notifyPropertyChanged(BR.tspStartCityPosition)
        }
    }

    val graph: Graph<String>
        get() = graphObservable.value!!

    var bestPath : ObservableList<String> = ObservableList(mutableListOf())

    @Bindable
    var distance: Int? = null

    @Bindable
    var tspStartCityPosition: Int = graph.getNodes().size

    fun findBestPath() {
        when (tspStartCityPosition) {
            graph.getNodes().size -> graph.travelingSalesMan()
            else -> graph.travelingSalesMan(tspStartCityPosition)
        }.let {
            this.distance = it.distance
            bestPath.clear()
            bestPath.addAll(it.path)
        }
        notifyPropertyChanged(BR.distance)
    }
    init {
    }
}