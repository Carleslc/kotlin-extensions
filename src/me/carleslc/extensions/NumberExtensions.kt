package me.carleslc.extensions

public fun Int.even() = this % 2 == 0

public fun Int.odd() = !even()

public fun Int.toBoolean() = when (this) {
	0 -> false
	else -> true
}

public fun Boolean.toInt() = when (this) {
	false -> 0
	true -> -1 // All bits to 1, supports bitwise not to be false
}

public fun Boolean.toDouble() = toInt().toDouble()

public fun Double.toBoolean() = toInt().toBoolean()

public inline fun <T> Int.timesIndexed(predicate: (Int) -> T) = (1..this).forEach { predicate(it) }

public inline fun <T> Int.timesIndexedToListOf(predicate: (Int) -> T) = (1..this).map { predicate(it) }

public inline fun <reified T> Int.timesIndexedToArrayOf(predicate: (Int) -> T) = timesIndexedToListOf { predicate(it) }.toTypedArray()

public inline fun <T> Int.times(predicate: () -> T) = timesIndexed { predicate() }

public inline fun <T> Int.timesToListOf(predicate: () -> T) = timesIndexedToListOf { predicate() }

public inline fun <reified T> Int.timesToArrayOf(predicate: () -> T) = timesIndexedToArrayOf { predicate() }
