package me.carleslc.kotlin.extensions.utils

public inline fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

public fun <T> joinToString(
		separator: CharSequence = ", ",
		prefix: CharSequence = "",
		postfix: CharSequence = "",
		limit: Int = -1,
		truncated: CharSequence = "...",
		transform: ((T) -> CharSequence)? = null,
		vararg params: T) = params.joinToString(separator, prefix, postfix, limit, truncated, transform)

public fun concat(vararg params: Any?) = params.joinToString(" ")
