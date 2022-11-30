package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mauzerov.travelingsalesguyproblem.graph.AutoGeneratingGraph
import com.mauzerov.travelingsalesguyproblem.graph.Graph

class MainActivityViewModel : BaseObservable() {
    // Live Data List Of City Names
    val citiesLiveData: LiveData<MutableList<String>> by lazy {
        MutableLiveData(
            mutableListOf<String>()
        )
    }

    val graphObservable = MutableLiveData(
            AutoGeneratingGraph<String>()
    ).apply {
        value?.addObserver { postValue(value) }
    }

    val graph: Graph<String>
        get() = graphObservable.value!!

    init {
        graph.addNode("ABC")
        graph.addNode("ADC")
    }
}