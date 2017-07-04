package me.carleslc.kotlin.extensions.map

public fun <K, V> Map<K, V>.joinToString(
		separator: CharSequence = ", ",
		prefix: CharSequence = "{ ",
		postfix: CharSequence = " }",
		limit: Int = -1,
		truncated: CharSequence = "...",
		transform: (Pair<K, V>) -> CharSequence = { it.joinToString() })
				= toList().joinToString(separator, prefix, postfix, limit, truncated, transform)
