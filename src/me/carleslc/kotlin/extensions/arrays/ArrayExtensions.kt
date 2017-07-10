@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.arrays

import me.carleslc.kotlin.extensions.collections.timesToListOf
import java.util.Random

typealias Matrix<T> = Array<Array<T>>

object A {
    inline operator fun <reified T> get(vararg ts: T) = Array<T>(ts.size) { i -> ts[i] }
}

inline fun <reified T> Int.timesToArrayOf(predicate: (Int) -> T) = timesToListOf(predicate).toTypedArray()

inline fun <T> Array<T>?.isBlank(): Boolean = this == null || isEmpty()

inline fun <T> Array<T?>.anyNull(): Boolean = any { it == null }

inline fun <T> Array<T?>.allNull(): Boolean = all { it == null }

inline fun <T> Array<Array<T?>>.anyNull(): Boolean = any { it.any { it == null } }

inline fun <T> Array<Array<T?>>.allNull(): Boolean = all { it.all { it == null } }

inline fun <T> Array<Array<T>>.anyInner(predicate: (T) -> Boolean): Boolean = any { it.any(predicate) }

inline fun <T> Array<Array<T>>.allInner(predicate: (T) -> Boolean): Boolean = all { it.all(predicate) }

fun <T> Array<T>.swap(i: Int, j: Int): Array<T> {
    return apply {
        val aux = this[i]
        this[i] = this[j]
        this[j] = aux
    }
}

inline fun <T> Array<T>.getRandom(generator: Random = Random()): T = get(generator.nextInt(size))

inline fun <reified T> Array<T>.shuffle(generator: Random = Random()): Array<T> {
    return apply {
        for (i in size downTo 2)
            swap(i - 1, generator.nextInt(i))
    }
}

inline fun <T> array2dOf(): Array<Array<T>> = arrayOf()

inline fun <T> array2dOf(vararg ts: Array<T>) = Array<Array<T>>(ts.size) { ts[it] }

inline fun <T> array2d(rows: Int, initCol: (Int) -> Array<T>) = Array<Array<T>>(rows, { row -> initCol(row) })

inline fun <reified T> matrix(rows: Int, cols: Int, init: (Int, Int) -> T): Matrix<T> {
    return array2d(rows) { row -> Array<T>(cols, { col -> init(row, col) }) }
}

inline fun <reified T> matrixOfNulls(rows: Int, cols: Int): Matrix<T> = matrix(rows, cols, { _, _ -> null as T })

inline val <T> Array<Array<T>>.rows get() = indices

inline val <T> Matrix<T>.columns get() = if (isEmpty()) (0..-1) else this[0].indices

inline val <T> Array<Array<T>>.lastIndexRows get() = lastIndex

inline val <T> Matrix<T>.lastIndexColumns get() = if (isEmpty()) -1 else this[0].lastIndex

inline val <T> Array<Array<T>>.rowSize get() = size

inline val <T> Matrix<T>.columnSize get() = lastIndexColumns + 1

inline val <T> Array<Array<T>>.totalSize get() = fold(0) { acc, col -> acc + col.size }
