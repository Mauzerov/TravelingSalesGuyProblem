package com.mauzerov.travelingsalesguyproblem

import androidx.databinding.BaseObservable

class MainActivityViewModel : BaseObservable() {
    val cities = mutableListOf<String>("ABC", "DEC")
}