package com.mauzerov.travelingsalesguyproblem.graph

import com.mauzerov.travelingsalesguyproblem.util.ObservableList
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class AutoGeneratingGraph<T> : Graph<T> {
    private val nodes: ObservableList<T> = ObservableList(mutableListOf())
    private val observers: MutableList<() -> Unit> = mutableListOf()

    private val matrix = MutableList(0) { MutableList(0) { 0 } }
    private var nodeCount = nodes.size

    fun addObserver(observer :  () -> Unit) {
        observers.add(observer)
    }

    override fun getNodes(): List<T> {
        return nodes
    }

    private val edges: MutableList<Graph.Edge<T>> = mutableListOf()

    override fun getEdges(): List<Graph.Edge<T>> {
        return edges
    }

    override fun addNode(node: T) {
        nodes.add(node)
        matrix.add(MutableList(nodes.size - 1) { Random.nextInt(1, 100) })
    }

    override fun removeNode(node: T) {
        val index = nodes.indexOfFirst { it == node }
        nodes.removeAt(index)
        matrix.removeAt(index)
        for (i in index until nodes.size) {
            matrix[i].removeAt(index)
        }
    }

    override operator fun get(fromIndex: Int, toIndex: Int) : Int? {
        return try {
            matrix[max(fromIndex, toIndex)][min(fromIndex, toIndex)]
        } catch (_: java.lang.IndexOutOfBoundsException) { null }
    }

    override operator fun get(index: Int) : T {
        return nodes[index]
    }

    override operator fun set(fromIndex: Int, toIndex: Int, weight: Int) {
        matrix[max(fromIndex, toIndex)][min(fromIndex, toIndex)] = weight
    }

    override operator fun set(index: Int, node: T) {
        nodes[index] = node
    }

    override fun removeEdge(index: Int) {
        edges.removeAt(index)
    }

    init {
        nodes.addObserver { _, arg ->
            observers.forEach { it() }

            (arg as? ObservableList.Argument<*>)?.let { argument ->
                val (updateType, node) = argument

                if (updateType == ObservableList.ChangeType.REMOVE) {
                    for (edge in edges.filter { it.from == node || it.to == node }) {
                        edges.remove(edge)
                    }
                }
            }
        }
    }

//    override fun getEdgeWeight(from: T, to: T): Int? {
//        return edges.firstOrNull {
//            it.from == from && it.to == to ||
//            it.from == to && it.to == from
//        }?.weight
//    }
//
//    override fun getEdgeWeight(fromIndex: Int, toIndex: Int): Int? {
//        if (fromIndex !in nodes.indices || toIndex !in nodes.indices) {
//            return null
//        }
//
//        return getEdgeWeight(nodes[fromIndex], nodes[toIndex])
//    }
//
//    override fun setEdgeWeight(from: T, to: T, weight: Int){
//        edges.firstOrNull {
//            it.from == from && it.to == to ||
//            it.from == to && it.to == from
//        }?.weight = weight
//    }
//
//    override fun setEdgeWeight(fromIndex: Int, toIndex: Int, weight: Int){
//        if (fromIndex !in nodes.indices || toIndex !in nodes.indices) {
//            return
//        }
//        setEdgeWeight(nodes[fromIndex], nodes[toIndex], weight)
//    }
}