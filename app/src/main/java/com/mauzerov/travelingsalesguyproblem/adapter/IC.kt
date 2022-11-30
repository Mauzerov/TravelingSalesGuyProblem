package com.mauzerov.travelingsalesguyproblem.adapter

import androidx.databinding.InverseMethod

object IC {
    @JvmStatic
    fun <T>getElement(array: List<T>, index : Int) : T? {
        return array.getOrNull(index)
    }

    @JvmStatic
    fun nullableIntToString(n: Int?) : String {
        return n?.toString() ?: ""
    }

    @JvmStatic
    @InverseMethod("nullableIntToString")
    fun stringToNullableInt(string: String) : Int? {
        return string.toIntOrNull()
    }
}