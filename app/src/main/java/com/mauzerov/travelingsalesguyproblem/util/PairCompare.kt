package com.mauzerov.travelingsalesguyproblem.util

fun <T, K>pairComparator(
    first: Comparator<T>,
    second: Comparator<K>,
) : Comparator<Pair<T, K>> {
    return compareBy<Pair<T, K>, T>(first) { it.first }
        .thenBy(second) { it.second }
}

fun <T : Comparable<*>, K: Comparable<*>>pairComparator() : Comparator<Pair<T, K>> {
    return compareBy<Pair<T, K>> { it.first }.thenBy { it.second }
}

