package com.mauzerov.travelingsalesguyproblem.algorithms

import com.mauzerov.travelingsalesguyproblem.graph.Graph
import com.mauzerov.travelingsalesguyproblem.util.DoubleArgumentMap
import com.mauzerov.travelingsalesguyproblem.util.pairComparator

data class TravelingSalesManResult<T>(
    val distance: Int,
    val path: List<T>
)

fun <T> Graph<T>.travelingSalesMan(startNode: Int) : TravelingSalesManResult<T> {
    /*
    function algorithm TSP (G, n) is
            for k := 2 to n do
    g({k}, k) := d(1, k)
    end for

        for s := 2 to n−1 do
    for all S ⊆ {2, ..., n}, |S| = s do
    for all k ∈ S do
        g(S, k) := minm≠k,m∈S [g(S\{k}, m) + d(m, k)]
    end for
        end for
        end for

        opt := mink≠1 [g({2, 3, ..., n}, k) + d(k, 1)]
    return (opt)
    end function*/
    val n = getNodes().size
    val cache = DoubleArgumentMap<Int, Pair<Int, Int>>()

    for (k in 0 until n) {
        if (k == startNode) continue
        cache[1 shl k, k] = Pair(this[startNode, k]!!, startNode)
    }

    for (sub_size in 2 until n) {
        for (comb in getNodes().indices.combinations(sub_size)) {
            var bits = 0
            for (bit in comb)
                bits = bits or (1 shl bit)

            for (k in comb) {
                val prev = bits and (1 shl k).inv()

                val res = mutableListOf<Pair<Int, Int>>()
                for (m in comb) {
                    if (m == 0 || m == k) continue
                    cache[prev, m]?.first?.let {
                        res.add(Pair(it + this[m, k]!!, m))
                    }
                }
                res.minWithOrNull(pairComparator())?.let {
                    cache[bits, k] = it
                }
            }
        }
    }
    var bits = (1 shl n) - 2
    val res = mutableListOf<Pair<Int, Int>>()

    for (k in 0 until n) {
        if (k == startNode) continue
        cache[bits, k]?.first?.let {
            res.add(Pair(it + this[k, startNode]!!, k))
        }
    }
    var (otp, parent) = res.minWith(pairComparator())

    val path = mutableListOf<T>()

    for (i in 0 until n - 1) {
        path.add(this[parent])
        val new = bits and (1 shl parent).inv()
        parent = cache[bits, parent]?.second ?: throw NullPointerException()
        bits = new
    }
    path.add(this[startNode])
    return TravelingSalesManResult<T>(distance=otp, path=path.toList())
}

fun <T> Graph<T>.travelingSalesMan() : TravelingSalesManResult<T> {
    return getNodes().indices.map {
        travelingSalesMan(it)
    }.minBy { it.distance }
}