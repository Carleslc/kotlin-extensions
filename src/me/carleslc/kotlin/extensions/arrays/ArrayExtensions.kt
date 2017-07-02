package me.carleslc.kotlin.extensions.arrays

import me.carleslc.kotlin.extensions.collections.timesIndexedToListOf

typealias Matrix<T> = Array<Array<T>>

public object A {
    inline operator fun <reified T> get(vararg ts: T): Array<T> = Array<T>(ts.size) { i -> ts[i] }
}

public inline fun <reified T> Int.timesIndexedToArrayOf(predicate: (Int) -> T) = timesIndexedToListOf { predicate(it) }.toTypedArray()

public inline fun <reified T> Int.timesToArrayOf(predicate: () -> T) = timesIndexedToArrayOf { predicate() }

public fun <T> Array<T?>.anyNull(): Boolean = any { it == null }

public fun <T> Array<T?>.allNull(): Boolean = all { it == null }

public fun <T> Array<Array<T?>>.anyNull(): Boolean = any { it.any { it == null } }

public fun <T> Array<Array<T?>>.allNull(): Boolean = all { it.all { it == null } }

public inline fun <T> Array<Array<T>>.anyInner(predicate: (T) -> Boolean): Boolean = any { it.any(predicate) }

public inline fun <T> Array<Array<T>>.allInner(predicate: (T) -> Boolean): Boolean = all { it.all(predicate) }

public fun <T> array2dOf(): Array<Array<T>> = arrayOf()

public fun <T> array2dOf(vararg ts: Array<T>) = Array<Array<T>>(ts.size) { ts[it] }

public inline fun <T> array2d(rows: Int, initCol: (Int) -> Array<T>) = Array<Array<T>>(rows, { row -> initCol(row) })

public inline fun <reified T> matrix(rows: Int, cols: Int, init: (Int, Int) -> T): Matrix<T> {
	return array2d(rows) { row -> Array<T>(cols, { col -> init(row, col) }) }
}

public inline fun <reified T> matrixOfNulls(rows: Int, cols: Int): Matrix<T> = matrix(rows, cols, { _,_ -> null as T })

public val <T> Array<Array<T>>.rows
	get() = indices

public val <T> Matrix<T>.columns
	get() = if (isEmpty()) (0..-1) else this[0].indices

public val <T> Array<Array<T>>.lastIndexRows
	get() = lastIndex

public val <T> Matrix<T>.lastIndexColumns
	get() = if (isEmpty()) -1 else this[0].lastIndex

public val <T> Array<Array<T>>.rowSize
	get() = size

public val <T> Matrix<T>.columnSize
	get() = lastIndexColumns + 1

public val <T> Array<Array<T>>.totalSize
	get() = fold(0) { acc, col -> acc + col.size }
