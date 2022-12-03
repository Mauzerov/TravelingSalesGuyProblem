package com.mauzerov.travelingsalesguyproblem.util

class DoubleArgumentMap<T, K> {
    private var holder = mutableMapOf<T, MutableMap<T, K>>()

    operator fun get(first: T, second: T) : K? {
        return holder[first]?.get(second)
    }

    operator fun set(first: T, second: T, value: K) {
        holder.getOrPut(first) {
            mutableMapOf()
        }[second] = value
    }
}