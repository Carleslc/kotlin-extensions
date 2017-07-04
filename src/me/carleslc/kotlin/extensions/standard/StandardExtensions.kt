package me.carleslc.kotlin.extensions.standard

public fun FIXME(): Nothing = throw Error("An operation needs a fix.")

public fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

public inline infix fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

public inline fun <T, R> T?.letOr(nullValue: R, block: (T) -> R) = this?.let(block) ?: nullValue
