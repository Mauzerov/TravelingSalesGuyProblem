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

    // Todo: add remove node by index

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
}