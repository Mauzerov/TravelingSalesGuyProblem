package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.properties.Delegates

class MainActivityViewModel : BaseObservable() {
    // Live Data List Of City Names
    val citiesLiveData: LiveData<MutableList<String>> by lazy {
        MutableLiveData(
            mutableListOf<String>()
        )
    }

    val graph =
        MutableLiveData(
            AutoGeneratingGraph<String>()
        )

    init {
        graph.value?.addNode("ABC")
        graph.value?.addNode("ADC")
    }
}