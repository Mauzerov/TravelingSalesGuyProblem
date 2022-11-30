package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mauzerov.travelingsalesguyproblem.graph.AutoGeneratingGraph

class MainActivityViewModel : BaseObservable() {
    // Live Data List Of City Names
    val citiesLiveData: LiveData<MutableList<String>> by lazy {
        MutableLiveData(
            mutableListOf<String>()
        )
    }

    val graph = MutableLiveData(
            AutoGeneratingGraph<String>()
    ).apply {
        value?.addObserver { postValue(value) }
    }

    init {
        graph.value?.addNode("ABC")
        graph.value?.addNode("ADC")
    }
}