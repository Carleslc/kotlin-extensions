package me.carleslc.kotlin.extensions.standard

import me.carleslc.kotlin.extensions.number.*
import org.funktionale.tries.Try

public fun FIXME(): Nothing = throw Error("An operation needs a fix.")

public fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

public inline infix fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

public inline fun <T, R> T?.letOrElse(nullValue: R, block: (T) -> R) = this?.let(block) ?: nullValue

public fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C = { a, b -> this(b, a) }
