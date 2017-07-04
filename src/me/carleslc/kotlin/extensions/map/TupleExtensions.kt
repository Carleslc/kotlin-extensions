package me.carleslc.kotlin.extensions.map

public fun <K, V> Pair<K, V>.joinToString(
		separator: CharSequence = ": ",
		prefix: CharSequence = "",
		postfix: CharSequence = "",
		firstTransform: (K) -> CharSequence = { it.toString() },
		secondTransform: (V) -> CharSequence = { if (it is CharSequence) "$it" else it.toString() })
				= "$prefix${ firstTransform(first) }$separator${ secondTransform(second) }$postfix"
