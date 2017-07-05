package me.carleslc.kotlin.extensions.standard

import org.funktionale.partials.*

public fun FIXME(): Nothing = throw Error("An operation needs a fix.")

public fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

public inline infix fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

public inline fun <T, R> T?.letOrElse(nullBlock: () -> R, block: (T) -> R): R = this?.let(block) ?: nullBlock()

public inline fun <T, R> T?.letOrElse(nullValue: R, block: (T) -> R): R = letOrElse({nullValue}, block)

public infix fun <T> (() -> T).butBefore(block: () -> Unit): () -> T = { block(); this() }

public infix fun <T, R> ((T) -> R).butBefore(block: () -> T): () -> R = { this(block()) }

public infix fun <T, R> (() -> T).andThen(block: (T) -> R): () -> R = { block(this()) }

public fun <T, R> (() -> T).andReturn(value: R): () -> R = andThen { value }

public fun <T> (() -> T).returnUnit(): () -> Unit = andReturn(Unit)

public fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C = { a, b -> this(b, a) }

public fun <T, R> ((T) -> R).with(param: T): () -> R = bind(param)

public fun <T1, T2, R> ((T1, T2) -> R).with(param1: T1, param2: T2): () -> R = partially1(param1).with(param2)

public fun <T1, T2, T3, R> ((T1, T2, T3) -> R).with(param1: T1, param2: T2, param3: T3): () -> R = partially1(param1).with(param2, param3)

public fun <T1, T2, T3, T4, R> ((T1, T2, T3, T4) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4): () -> R = partially1(param1).with(param2, param3, param4)

public fun <T1, T2, T3, T4, T5, R> ((T1, T2, T3, T4, T5) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5): () -> R = partially1(param1).with(param2, param3, param4, param5)

public fun <T1, T2, T3, T4, T5, T6, R> ((T1, T2, T3, T4, T5, T6) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6): () -> R = partially1(param1).with(param2, param3, param4, param5, param6)
