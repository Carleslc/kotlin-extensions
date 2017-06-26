package me.carleslc.extensions

object A {
    inline operator fun <reified T> get(vararg ts: T): Array<T> = Array<T>(ts.size) { i -> ts[i] }
}

public fun <T> Array<T?>.anyNull(): Boolean = any { it == null }

public fun <T> Array<T?>.allNull(): Boolean = all { it == null }

public fun <T> Array<Array<T?>>.anyNull(): Boolean = any { it.any { it == null } }

public fun <T> Array<Array<T?>>.allNull(): Boolean = all { it.all { it == null } }

public inline fun <T> Array<Array<T>>.anyInner(predicate: (T) -> Boolean): Boolean = any { it.any(predicate) }

public inline fun <T> Array<Array<T>>.allInner(predicate: (T) -> Boolean): Boolean = all { it.all(predicate) }

public fun <T> array2dOf(): Array<Array<T>> = arrayOf()

public fun <T> array2dOf(vararg ts: Array<T>) = Array<Array<T>>(ts.size) { ts[it] }

public inline fun <T> array2d(rows: Int, initCol: (Int) -> Array<T>) = Array<Array<T>>(rows, { row -> initCol(row) })

public inline fun <reified T> array2d(rows: Int, cols: Int, init: (Int, Int) -> T): Array<Array<T>> {
	return array2d(rows) { row -> Array<T>(cols, { col -> init(row, col) }) }
}

public inline fun <reified T> array2dOfNulls(rows: Int, cols: Int): Array<Array<T?>> = array2d(rows, cols, { _,_ -> null })

public val <T> Array<Array<T>>.rows
	get() = indices

public val <T> Array<Array<T>>.columns
	get() = if (isEmpty()) (0..-1) else this[0].indices

public val <T> Array<Array<T>>.lastIndexRows
	get() = lastIndex

public val <T> Array<Array<T>>.lastIndexColumns
	get() = if (isEmpty()) -1 else this[0].lastIndex

public val <T> Array<Array<T>>.rowSize
	get() = size

public val <T> Array<Array<T>>.columnSize
	get() = lastIndexColumns + 1

public val <T> Array<Array<T>>.totalSize
	get() = fold(0) { acc, col -> acc + col.size }
