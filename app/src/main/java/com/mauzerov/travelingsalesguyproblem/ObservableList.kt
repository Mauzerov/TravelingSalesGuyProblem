package com.mauzerov.travelingsalesguyproblem

import java.util.*

class ObservableList<T>(private val wrapped: MutableList<T>): MutableList<T> by wrapped, Observable() {
    enum class ChangeType {
        ADD, REMOVE, SET
    }
    data class Argument<T>(val updateType: ChangeType, val element: T)

    override fun add(element: T): Boolean {
        return if (wrapped.add(element)) {
            setChanged()
            notifyObservers(Argument(ChangeType.ADD, element))
            true
        }
        else false
    }

    override fun remove(element: T): Boolean {
        return if (wrapped.remove(element)) {
            setChanged()
            notifyObservers(Argument(ChangeType.REMOVE, element))
            true
        }
        else false
    }

    override fun removeAt(index: Int): T {
        val `return` = this[index]
        if (wrapped.remove(this[index])) {
            setChanged()
            notifyObservers(Argument(ChangeType.REMOVE, `return`))
            return`return`
        }
        throw IndexOutOfBoundsException()
    }
}