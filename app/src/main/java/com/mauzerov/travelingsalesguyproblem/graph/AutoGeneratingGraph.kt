package com.mauzerov.travelingsalesguyproblem.graph

import com.mauzerov.travelingsalesguyproblem.util.ObservableList

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

    override fun getWeight(from: T, to: T): Int {
        return edges.first { it.from == from && it.to == to }.weight
    }

    override fun getWeight(fromIndex: Int, toIndex: Int): Int {
        return edges.first { it.from == nodes[fromIndex] && it.to == nodes[toIndex] }.weight
    }
}