@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.standard

import arrow.data.Try
import arrow.data.getOrDefault
import arrow.data.getOrElse
import me.carleslc.kotlin.extensions.preconditions.requireSize
import java.io.PrintStream
import java.math.BigDecimal
import java.math.BigInteger

inline fun FIXME(): Nothing = throw Error("An operation needs a fix.")

inline fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

inline fun loop(block: () -> Unit) = run { while (true) block() }

inline infix operator fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

inline infix fun <T> T.with(noinline block: (T.() -> T)?): T = block?.invoke(this) ?: this

inline fun <T> T.asNullable(): T? = this

inline fun <T> T?.isNull(): Boolean = this == null
inline fun <T> T?.isNotNull(): Boolean = this != null

inline fun defaultByte(): Byte = 0
inline fun defaultShort(): Short = 0
inline fun defaultInt(): Int = 0
inline fun defaultLong(): Long = 0L
inline fun defaultFloat(): Float = 0f
inline fun defaultDouble(): Double = 0.0
inline fun defaultBoolean(): Boolean = false
inline fun defaultBigInteger(): BigInteger = BigInteger.ZERO
inline fun defaultBigDecimal(): BigDecimal = BigDecimal.ZERO
inline fun defaultString(): String = ""

inline fun <T, R> T?.letOrElse(nullBlock: () -> R, block: (T) -> R): R = this?.let(block) ?: nullBlock()

inline fun <T, R> T?.letOrElse(nullValue: R, block: (T) -> R): R = letOrElse({ nullValue }, block)

inline fun Boolean.letIfTrue(block: () -> Unit): Unit = letIfTrue(block, {})

inline fun Boolean.letIfFalse(block: () -> Unit) = letIfTrue({}, block)

inline fun <R> Boolean.letIfTrue(ifBlock: () -> R, elseBlock: () -> R): R = letIf({ this }, { run(ifBlock) }, { run(elseBlock) })

inline fun <T> T.letIf(condition: T.() -> Boolean, ifBlock: (T) -> Unit): Unit = letIf(condition, ifBlock) {}

inline fun <T, R> T.letIf(condition: T.() -> Boolean, ifBlock: (T) -> R, elseBlock: (T) -> R): R = if (run(condition)) run(ifBlock) else run(elseBlock)

inline fun Boolean.alsoIfTrue(block: () -> Unit): Boolean = alsoIfTrue(block, {})

inline fun Boolean.alsoIfFalse(block: () -> Unit): Boolean = alsoIfTrue({}, block)

inline fun Boolean.alsoIfTrue(ifBlock: () -> Unit, elseBlock: () -> Unit): Boolean = alsoIf({ this }, { run(ifBlock) }, { run(elseBlock) })

inline fun <T> T.alsoIf(condition: T.() -> Boolean, ifBlock: (T) -> Unit): T = alsoIf(condition, ifBlock) {}

inline fun <T, R> T.alsoIf(condition: T.() -> Boolean, ifBlock: (T) -> R, elseBlock: (T) -> R): T = also { if (run(condition)) run(ifBlock) else run(elseBlock) }

inline infix fun <T> (() -> T).butBefore(crossinline block: () -> Unit): () -> T = { block(); this() }

inline infix fun <T, R> ((T) -> R).butBefore(crossinline block: () -> T): () -> R = { this(block()) }

inline infix fun <T, R> (() -> T).andThen(crossinline block: T.() -> R): () -> R = { block(this()) }

inline infix fun <T> (() -> T).andThrow(crossinline throwable: T.() -> Throwable): () -> Nothing = andThen { throw throwable() }
inline infix fun <T> (() -> T).andThrow(throwable: Throwable): () -> Nothing = andThrow { throwable }

inline infix fun <T, R> (() -> T).andReturn(value: R): () -> R = andThen { value }

inline fun <T, R> (() -> T).returnValue(value: R): () -> R = andReturn(value)
inline fun <T> (() -> T).returnByte(): () -> Byte = andReturn(defaultByte())
inline fun <T> (() -> T).returnShort(): () -> Short = andReturn(defaultShort())
inline fun <T> (() -> T).returnInt(): () -> Int = andReturn(defaultInt())
inline fun <T> (() -> T).returnLong(): () -> Long = andReturn(defaultLong())
inline fun <T> (() -> T).returnFloat(): () -> Float = andReturn(defaultFloat())
inline fun <T> (() -> T).returnDouble(): () -> Double = andReturn(defaultDouble())
inline fun <T> (() -> T).returnBigInteger(): () -> BigInteger = andReturn(defaultBigInteger())
inline fun <T> (() -> T).returnBigDecimal(): () -> BigDecimal = andReturn(defaultBigDecimal())
inline fun <T> (() -> T).returnString(): () -> String = andReturn(defaultString())
inline fun <T> (() -> T).returnTrue(): () -> Boolean = andReturn(true)
inline fun <T> (() -> T).returnFalse(): () -> Boolean = andReturn(false)
inline fun <T> (() -> T).returnBoolean(): () -> Boolean = andReturn(defaultBoolean())
inline fun <T> (() -> T).returnNull(): () -> T? = andReturn(null)
inline fun <T> (() -> T).returnUnit(): () -> Unit = andReturn(Unit)

inline fun <T> T.print(outStream: PrintStream = System.out, noinline transform: (String) -> String = { "$it = " }): T = also { outStream.print(toString().with(transform)) }

inline fun <T, R> T.println(outStream: PrintStream = System.out, what: (T) -> R): R = what(this).apply { outStream.println(this) }

inline fun <T> T.println(tag: String = "", separator: String = " = ", outStream: PrintStream = System.out): T = also { outStream.println(if (tag.isBlank()) it else "$tag$separator$it") }

inline fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C = { a, b -> this(b, a) }

inline fun <R> (() -> R).run() = invoke()

inline infix fun <T, R> ((T) -> R).bind(param: T): () -> R = with(param)

inline infix fun <T, R> ((T) -> R).with(param: T): () -> R = { this(param) }

inline fun <T1, T2, R> ((T1, T2) -> R).with(param1: T1, param2: T2): () -> R = { this(param1, param2) }

inline fun <T1, T2, T3, R> ((T1, T2, T3) -> R).with(param1: T1, param2: T2, param3: T3): () -> R = { this(param1, param2, param3) }

inline fun <T1, T2, T3, T4, R> ((T1, T2, T3, T4) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4): () -> R = { this(param1, param2, param3, param4) }

inline fun <T1, T2, T3, T4, T5, R> ((T1, T2, T3, T4, T5) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5): () -> R = { this(param1, param2, param3, param4, param5) }

inline fun <T1, T2, T3, T4, T5, T6, R> ((T1, T2, T3, T4, T5, T6) -> R).with(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6): () -> R = { this(param1, param2, param3, param4, param5, param6) }

inline fun <T, R> ((T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(1); this(a[0]) }

inline fun <T, R> ((T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(2); this(a[0], a[1]) }

inline fun <T, R> ((T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(3); this(a[0], a[1], a[2]) }

inline fun <T, R> ((T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(4); this(a[0], a[1], a[2], a[3]) }

inline fun <T, R> ((T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(5); this(a[0], a[1], a[2], a[3], a[4]) }

inline fun <T, R> ((T, T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(6); this(a[0], a[1], a[2], a[3], a[4], a[5]) }

inline fun Try<Boolean>.getOrTrue() = getOrElse { true }

inline fun Try<Boolean>.getOrFalse() = getOrElse { false }

inline fun <T> Try<T>.getOrNull(): T? = getOrDefault { null }
