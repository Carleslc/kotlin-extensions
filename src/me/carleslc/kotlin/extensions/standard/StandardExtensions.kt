package me.carleslc.kotlin.extensions.standard

public inline infix fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

public inline fun <T, R> T?.letOr(nullValue: R, block: (T) -> R) = this?.let(block) ?: nullValue
