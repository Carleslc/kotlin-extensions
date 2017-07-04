package me.carleslc.kotlin.extensions.tuples

public fun <A, B> Pair<A, B>.joinToString(
		separator: CharSequence = ", ",
		prefix: CharSequence = "",
		postfix: CharSequence = "",
		firstTransform: (A) -> CharSequence = { it.toString() },
		secondTransform: (B) -> CharSequence = { it.toString() })
				= "$prefix${ firstTransform(first) }$separator${ secondTransform(second) }$postfix"

public fun <A, B, C> Triple<A, B, C>.joinToString(
		separator: CharSequence = ", ",
		prefix: CharSequence = "",
		postfix: CharSequence = "",
		firstTransform: (A) -> CharSequence = { it.toString() },
		secondTransform: (B) -> CharSequence = { it.toString() },
		thirdTransform: (C) -> CharSequence = { it.toString() })
				= "$prefix${ firstTransform(first) }$separator${ secondTransform(second) }$separator${ thirdTransform(third) }$postfix"
