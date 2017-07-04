package me.carleslc.kotlin.extensions.map

import me.carleslc.kotlin.extensions.tuples.joinToString
import me.carleslc.kotlin.extensions.strings.wrapString

public fun <K, V> Map<K, V>.joinToString(
		separator: CharSequence = ", ",
		prefix: CharSequence = "{ ",
		postfix: CharSequence = " }",
		limit: Int = -1,
		truncated: CharSequence = "...",
		transform: (Pair<K, V>) -> CharSequence = { it.joinToString(": ", secondTransform = { it.wrapString() }) })
				= toList().joinToString(separator, prefix, postfix, limit, truncated, transform)
