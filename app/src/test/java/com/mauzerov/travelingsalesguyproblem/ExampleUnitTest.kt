package com.mauzerov.travelingsalesguyproblem

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun observableListOfGraph() {
        val graph = Graph<Int>()

        graph.addNode(1)
        graph.addNode(2)

        graph.addEdge(from = 1, 2, 1)

        graph.removeNode(1)
        assert(graph.edges.isEmpty())

        graph.addNode(1)
        graph.addEdge(from = 2, 1, 1)

        graph.addNode(3)
        graph.addEdge(from = 1, 3, 1)

        graph.removeNode(1)
        assert(graph.edges.isEmpty())

        assertEquals(graph.nodes.size, 2)
    }
}