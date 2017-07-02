package me.carleslc.extensions.number

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
