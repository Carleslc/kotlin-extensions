@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.standard

import me.carleslc.kotlin.extensions.preconditions.requireSize
import org.funktionale.partials.*
import org.funktionale.tries.Try

inline fun FIXME(): Nothing = throw Error("An operation needs a fix.")

inline fun FIXME(reason: String): Nothing = throw Error("An operation needs a fix: $reason")

inline fun loop(block: () -> Unit) = run { while (true) block() }

inline infix operator fun Int.times(predicate: (Int) -> Unit) = repeat(this, predicate)

inline infix fun <T> T.with(noinline block: (T.() -> T)?): T = block?.invoke(this) ?: this

inline fun <T> T.asNullable(): T? = this

inline fun <T> T?.isNull(): Boolean = this == null
inline fun <T> T?.isNotNull(): Boolean = this != null

inline fun <T, R> T?.to(value: R?): R? = value
inline fun <T> T?.toByte(): Byte = 0
inline fun <T> T?.toShort(): Short = 0
inline fun <T> T?.toInt(): Int = 0
inline fun <T> T?.toLong(): Long = 0L
inline fun <T> T?.toDouble(): Double = 0.0
inline fun <T> T?.toFloat(): Float = 0f
inline fun <T> T?.toTrue(): Boolean = true
inline fun <T> T?.toFalse(): Boolean = false
inline fun <T> T?.toBoolean(): Boolean = toFalse()
inline fun <T> T?.toUnit() = Unit

inline fun <T, R> T?.letOrElse(nullBlock: () -> R, block: (T) -> R): R = this?.let(block) ?: nullBlock()

inline fun <T, R> T?.letOrElse(nullValue: R, block: (T) -> R): R = letOrElse({ nullValue }, block)

inline fun Boolean.letIfTrue(ifBlock: () -> Unit): Unit = letIfTrue(ifBlock) {}

inline fun <R> Boolean.letIfTrue(ifBlock: () -> R, elseBlock: () -> R): R = letIf({ this }, { run(ifBlock) }, { run(elseBlock) })

inline fun <T> T.letIf(condition: (T) -> Boolean, ifBlock: (T) -> Unit): Unit = letIf(condition, ifBlock) {}

inline fun <T, R> T.letIf(condition: (T) -> Boolean, ifBlock: (T) -> R, elseBlock: (T) -> R): R = if (run(condition)) run(ifBlock) else run(elseBlock)

inline fun Boolean.alsoIfTrue(ifBlock: () -> Unit): Boolean = alsoIfTrue(ifBlock) {}

inline fun Boolean.alsoIfTrue(ifBlock: () -> Unit, elseBlock: () -> Unit): Boolean = alsoIf({ this }, { run(ifBlock) }, { run(elseBlock) })

inline fun <T> T.alsoIf(condition: (T) -> Boolean, ifBlock: (T) -> Unit): T = alsoIf(condition, ifBlock) {}

inline fun <T, R> T.alsoIf(condition: (T) -> Boolean, ifBlock: (T) -> R, elseBlock: (T) -> R): T = also { if (run(condition)) run(ifBlock) else run(elseBlock) }

inline infix fun <T> (() -> T).butBefore(crossinline block: () -> Unit): () -> T = { block(); this() }

inline infix fun <T, R> ((T) -> R).butBefore(crossinline block: () -> T): () -> R = { this(block()) }

inline infix fun <T, R> (() -> T).andThen(crossinline block: T.() -> R): () -> R = { block(this()) }

inline infix fun <T> (() -> T).andThrow(crossinline throwable: T.() -> Throwable): () -> Nothing = andThen { throw throwable() }
inline infix fun <T> (() -> T).andThrow(throwable: Throwable): () -> Nothing = andThrow { throwable }

inline infix fun <T, R> (() -> T).andReturn(value: R): () -> R = andThen { value }

inline fun <T, R> (() -> T).returnValue(value: R): () -> R = andReturn(value)
inline fun <T> (() -> T).returnByte(): () -> Byte = andReturn(0)
inline fun <T> (() -> T).returnShort(): () -> Short = andReturn(0)
inline fun <T> (() -> T).returnInt(): () -> Int = andReturn(0)
inline fun <T> (() -> T).returnLong(): () -> Long = andReturn(0L)
inline fun <T> (() -> T).returnFloat(): () -> Float = andReturn(0f)
inline fun <T> (() -> T).returnDouble(): () -> Double = andReturn(0.0)
inline fun <T> (() -> T).returnTrue(): () -> Boolean = andReturn(true)
inline fun <T> (() -> T).returnFalse(): () -> Boolean = andReturn(false)
inline fun <T> (() -> T).returnBoolean(): () -> Boolean = returnFalse()
inline fun <T> (() -> T).returnNull(): () -> T? = andReturn(null)
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

inline fun <T, R> ((T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(1); this(a[0]) }

inline fun <T, R> ((T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(2); this(a[0], a[1]) }

inline fun <T, R> ((T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(3); this(a[0], a[1], a[2]) }

inline fun <T, R> ((T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(4); this(a[0], a[1], a[2], a[3]) }

inline fun <T, R> ((T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(5); this(a[0], a[1], a[2], a[3], a[4]) }

inline fun <T, R> ((T, T, T, T, T, T) -> R).collapseParams(): (Array<T>) -> R = { a -> a.requireSize(6); this(a[0], a[1], a[2], a[3], a[4], a[5]) }

inline fun Try<Boolean>.getOrTrue() = getOrElse { true }

inline fun Try<Boolean>.getOrFalse() = getOrElse { false }

inline fun <T> Try<T>.getOrNull() = if (isFailure()) null else get()
