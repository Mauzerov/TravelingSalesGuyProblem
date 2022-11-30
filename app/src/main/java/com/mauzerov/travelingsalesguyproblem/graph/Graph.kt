package com.mauzerov.travelingsalesguyproblem.graph

interface Graph<T> {
    data class Edge<T>(
        val from: T,
        val to: T,
        val weight: Int
    )

    fun addNode(node: T)

    fun removeNode(node: T)

    fun addEdge(from: T, to: T, weight: Int)

    fun addEdge(fromIndex: Int, toIndex: Int, weight: Int)

    fun removeEdge(index: Int)

    fun getNodes(): List<T>

    fun getEdges(): List<Edge<T>>

    fun getWeight(from: T, to: T): Int

    fun getWeight(fromIndex: Int, toIndex: Int): Int
}