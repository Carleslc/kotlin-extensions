@file:Suppress("NOTHING_TO_INLINE")
package me.carleslc.kotlin.extensions.standard

import org.funktionale.partials.*
import org.funktionale.tries.Try

inline fun FIXME(): Nothing = throw Error("An operation needs a fix.")

inline fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

inline fun loop(block: () -> Unit) = run { while (true) block() }

inline infix operator fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

inline infix fun <T> T.with(noinline block: (T.() -> T)?): T = block?.invoke(this) ?: this

inline fun <T> T.asNullable(): T? = this

inline fun <T, R> T?.letOrElse(nullBlock: () -> R, block: (T) -> R): R = this?.let(block) ?: nullBlock()

inline fun <T, R> T?.letOrElse(nullValue: R, block: (T) -> R): R = letOrElse({nullValue}, block)

inline fun Boolean.letIf(ifBlock: () -> Unit) = letIf(ifBlock) {}

inline fun <R> Boolean.letIf(ifBlock: () -> R, elseBlock: () -> R): R = if (this) run(ifBlock) else run(elseBlock)

inline fun <T> T.letIf(condition: (T) -> Boolean, ifBlock: (T) -> Unit) = letIf(condition, ifBlock) {}

inline fun <T, R> T.letIf(condition: (T) -> Boolean, ifBlock: (T) -> R, elseBlock: (T) -> R): R = if (run(condition)) run(ifBlock) else run(elseBlock)

inline infix fun <T> (() -> T).butBefore(noinline block: () -> Unit): () -> T = { block(); this() }

inline infix fun <T, R> ((T) -> R).butBefore(noinline block: () -> T): () -> R = { this(block()) }

inline infix fun <T, R> (() -> T).andThen(noinline block: (T) -> R): () -> R = { block(this()) }

inline infix fun <T, R> (() -> T).andReturn(value: R): () -> R = andThen { value }

inline infix fun <T> (() -> Any?).returnValue(value: T): () -> T = andReturn(value)

inline fun <T> (() -> T).returnUnit(): () -> Unit = andReturn(Unit)

inline fun <T> T.print(noinline transform: (String) -> String = { "$it = " }): T = also { System.out.print(it.toString().with(transform)) }

inline fun <T> T.println(): T = also { println(this) }

inline fun <T, R> T.print(noinline transform: ((String) -> String)? = null, what: (T) -> R): R = what(this).apply { println(toString().with(transform)) }

inline fun <T> T.print(tag: String = "", separator: String = " = "): T = also { println(if (tag.isBlank()) it else "$tag$separator$it") }

inline fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C = { a, b -> this(b, a) }

inline fun <R> (() -> R).run() = invoke()

inline infix fun <T, R> ((T) -> R).with(param: T): () -> R = bind(param)

inline fun <T1, T2, R> ((T1, T2) -> R).with(param1: T1, param2: T2): () -> R = partially1(param1).with(param2)

inline fun <T1, T2, T3, R> ((T1, T2, T3) -> R).with(param1: T1, param2: T2, param3: T3): () -> R = partially1(param1).with(param2, param3)

inline fun <T1, T2, T3, T4, R> ((T1, T2, T3, T4) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4): () -> R = partially1(param1).with(param2, param3, param4)

inline fun <T1, T2, T3, T4, T5, R> ((T1, T2, T3, T4, T5) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5): () -> R = partially1(param1).with(param2, param3, param4, param5)

inline fun <T1, T2, T3, T4, T5, T6, R> ((T1, T2, T3, T4, T5, T6) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6): () -> R = partially1(param1).with(param2, param3, param4, param5, param6)

inline fun <T, R> ((T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0]) }

inline fun <T, R> ((T, T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0], it[1]) }

inline fun <T, R> ((T, T, T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0], it[1], it[2]) }

inline fun <T, R> ((T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0], it[1], it[2], it[3]) }

inline fun <T, R> ((T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0], it[1], it[2], it[3], it[4]) }

inline fun <T, R> ((T, T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { this(it[0], it[1], it[2], it[3], it[4], it[5]) }

inline fun Try<Boolean>.getOrTrue() = getOrElse { true }

inline fun Try<Boolean>.getOrFalse() = getOrElse { false }
