@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.tuples

inline fun <A, B> Pair<A, B>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        noinline firstTransform: (A) -> CharSequence = { it.toString() },
        noinline secondTransform: (B) -> CharSequence = { it.toString() })
        = "$prefix${firstTransform(first)}$separator${secondTransform(second)}$postfix"

inline fun <A, B, C> Triple<A, B, C>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        noinline firstTransform: (A) -> CharSequence = { it.toString() },
        noinline secondTransform: (B) -> CharSequence = { it.toString() },
        noinline thirdTransform: (C) -> CharSequence = { it.toString() })
        = "$prefix${firstTransform(first)}$separator${secondTransform(second)}$separator${thirdTransform(third)}$postfix"
