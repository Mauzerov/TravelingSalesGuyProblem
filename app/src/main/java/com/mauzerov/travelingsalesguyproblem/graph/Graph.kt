package com.mauzerov.travelingsalesguyproblem.graph

interface Graph<T> {
    data class Edge<T>(
        val from: T,
        val to: T,
        var weight: Int
    )

    fun addNode(node: T)

    fun removeNode(node: T)

    fun removeEdge(index: Int)

    fun getNodes(): List<T>

    fun getEdges(): List<Edge<T>>

    operator fun get(fromIndex: Int, toIndex: Int) : Int?

    operator fun get(index : Int) : T

    operator fun set(index: Int, node: T)

    operator fun set(fromIndex: Int, toIndex: Int, weight: Int)
}