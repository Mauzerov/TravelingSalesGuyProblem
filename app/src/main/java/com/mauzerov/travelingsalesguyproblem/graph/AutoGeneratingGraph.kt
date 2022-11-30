package com.mauzerov.travelingsalesguyproblem.graph

import androidx.databinding.InverseMethod
import com.mauzerov.travelingsalesguyproblem.util.ObservableList
import kotlin.random.Random

class AutoGeneratingGraph<T> : Graph<T> {
    private val nodes: ObservableList<T> = ObservableList(mutableListOf())
    private val observers: MutableList<() -> Unit> = mutableListOf()

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
        for (existingNode in nodes) {
            if (existingNode != node) {
                edges.add(Graph.Edge(existingNode, node, Random.nextInt(1,100)))
            }
        }
    }

    override fun removeNode(node: T) {
        nodes.remove(nodes.first { it == node })
    }

    override fun addEdge(from: T, to: T, weight: Int) {
        edges.add(Graph.Edge(from, to, weight))
    }

    override fun addEdge(fromIndex: Int, toIndex: Int, weight: Int) {
        edges.add(Graph.Edge(nodes[fromIndex], nodes[toIndex], weight))
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

    override fun getEdgeWeight(from: T, to: T): Int? {
        return edges.firstOrNull {
            it.from == from && it.to == to ||
            it.from == to && it.to == from
        }?.weight
    }

    override fun getEdgeWeight(fromIndex: Int, toIndex: Int): Int? {
        if (fromIndex !in nodes.indices || toIndex !in nodes.indices) {
            return null
        }

        return getEdgeWeight(nodes[fromIndex], nodes[toIndex])
    }

    override fun setEdgeWeight(from: T, to: T, weight: Int){
        edges.firstOrNull {
            it.from == from && it.to == to ||
            it.from == to && it.to == from
        }?.weight = weight
    }

    override fun setEdgeWeight(fromIndex: Int, toIndex: Int, weight: Int){
        if (fromIndex !in nodes.indices || toIndex !in nodes.indices) {
            return
        }
        setEdgeWeight(nodes[fromIndex], nodes[toIndex], weight)
    }
}