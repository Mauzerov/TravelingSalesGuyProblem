package com.mauzerov.travelingsalesguyproblem.algorithms

fun <T>Iterable<T>.combinations(r: Int) : Sequence<List<T>> = sequence {
    val pool = this@combinations as? List<T> ?: toList()
    val n = pool.size

    if (r > n) return@sequence
    val indices = IntArray(r) { it }

    while (true) {
        yield(indices.map { pool[it] })
        var i = r
        do {
            i--; if (i == -1) return@sequence
        } while (indices[i] == i + n - r )
        indices[i]++
        for (j in i+1 until r)
            indices[j] = indices[j - 1] + 1
    }
}