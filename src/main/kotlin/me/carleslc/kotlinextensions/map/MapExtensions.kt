@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlinextensions.map

import strings.wrapString
import me.carleslc.kotlinextensions.tuples.joinToString

object M {
    inline operator fun <K, V> get(vararg pairs: Pair<K, V>): Map<K, V> = mapOf(*pairs)
}

inline fun <K, V> Map<K, V>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "{ ",
        postfix: CharSequence = " }",
        limit: Int = -1,
        truncated: CharSequence = "...",
        noinline transform: (Pair<K, V>) -> CharSequence = { it.joinToString(": ", secondTransform = { wrapString() }) })
        = toList().joinToString(separator, prefix, postfix, limit, truncated, transform)
