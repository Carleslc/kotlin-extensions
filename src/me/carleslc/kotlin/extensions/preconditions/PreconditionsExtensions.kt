@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.preconditions

import me.carleslc.kotlin.extensions.standard.*
import java.math.BigDecimal
import java.math.BigInteger

inline fun <T> T.require(requirement: T.() -> Boolean, noinline throwable: T.() -> Throwable = { IllegalArgumentException("$this does not match requirements") }, noinline elseBlock: T.() -> Unit = {}): T = if (run(requirement)) this else {
    run(elseBlock.with(this).andThrow(throwable()))
}

inline fun <T> T.require(requirement: T.() -> Boolean, throwable: Throwable = IllegalArgumentException("$this does not match requirements"), noinline elseBlock: T.() -> Unit = {}): T = require(requirement, { throwable }, elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean, message: String = "$this does not match requirements", noinline elseBlock: T.() -> Unit = {}): T = require(requirement, IllegalArgumentException(message), elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean): T = require(requirement, "$this does not match requirements")

inline fun <T> T?.requireNotNull(throwable: Throwable = IllegalArgumentException("cannot be null"), noinline elseBlock: () -> Unit = {}): T = if (this != null) this else {
    run(elseBlock.andThrow(throwable))
}

inline fun <T> T?.requireNotNull(message: String = "cannot be null", noinline elseBlock: () -> Unit = {}): T = requireNotNull(IllegalArgumentException(message), elseBlock)
inline fun <T> T?.requireNotNull(): T = requireNotNull("cannot be null")

inline fun String?.requireNotBlank(throwable: Throwable = IllegalArgumentException("cannot be blank"), noinline elseBlock: () -> Unit = {}): String = if (!isNullOrBlank()) this!! else {
    run(elseBlock.andThrow(throwable))
}

inline fun String?.requireNotBlank(message: String = "cannot be blank", noinline elseBlock: () -> Unit = {}): String = requireNotBlank(IllegalArgumentException(message), elseBlock)
inline fun String?.requireNotBlank(): String = requireNotBlank("cannot be blank")

inline fun <T> Collection<T>.requireNotEmpty(throwable: Throwable = IllegalArgumentException("cannot be empty"), noinline elseBlock: Collection<T>.() -> Unit = {}): Collection<T> = require({ isNotEmpty() }, throwable, elseBlock)
inline fun <T> Collection<T>.requireNotEmpty(message: String = "cannot be empty", noinline elseBlock: Collection<T>.() -> Unit = {}): Collection<T> = requireNotEmpty(IllegalArgumentException(message), elseBlock)
inline fun <T> Collection<T>.requireNotEmpty(): Collection<T> = requireNotEmpty("cannot be empty")

inline fun <T> Array<T>.requireNotEmpty(throwable: Throwable = IllegalArgumentException("cannot be empty"), noinline elseBlock: Array<T>.() -> Unit = {}): Array<T> = require({ isNotEmpty() }, throwable, elseBlock)
inline fun <T> Array<T>.requireNotEmpty(message: String = "cannot be empty", noinline elseBlock: Array<T>.() -> Unit = {}): Array<T> = requireNotEmpty(IllegalArgumentException(message), elseBlock)
inline fun <T> Array<T>.requireNotEmpty(): Array<T> = requireNotEmpty(IllegalArgumentException("cannot be empty"))

inline fun <T> Collection<T>.requireSize(size: Int, throwable: Throwable = IllegalArgumentException("must be of size $size"), noinline elseBlock: Collection<T>.() -> Unit = {}): Collection<T> = require({ this.size == size }, throwable, elseBlock)
inline fun <T> Collection<T>.requireSize(size: Int, message: String = "must be of size $size", noinline elseBlock: Collection<T>.() -> Unit = {}): Collection<T> = requireSize(size, IllegalArgumentException(message), elseBlock)
inline fun <T> Collection<T>.requireSize(size: Int): Collection<T> = requireSize(size, "must be of size $size")

inline fun <T> Array<T>.requireSize(size: Int, throwable: Throwable = IllegalArgumentException("must be of size $size"), noinline elseBlock: Array<T>.() -> Unit = {}): Array<T> = require({ this.size == size }, throwable, elseBlock)
inline fun <T> Array<T>.requireSize(size: Int, message: String = "must be of size $size", noinline elseBlock: Array<T>.() -> Unit = {}): Array<T> = requireSize(size, IllegalArgumentException(message), elseBlock)
inline fun <T> Array<T>.requireSize(size: Int): Array<T> = requireSize(size, "must be of size $size")

fun Byte.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultByte()}"), elseBlock: () -> Unit = {}): Byte = require({ this != defaultByte() }, throwable, { run(elseBlock) })
fun Byte.requireNonDefault(message: String = "cannot be ${defaultByte()}", elseBlock: () -> Unit = {}): Byte = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Byte.requireNonDefault(): Byte = requireNonDefault("cannot be ${defaultByte()}")

fun Short.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultShort()}"), elseBlock: () -> Unit = {}): Short = require({ this != defaultShort() }, throwable, { run(elseBlock) })
fun Short.requireNonDefault(message: String = "cannot be ${defaultShort()}", elseBlock: () -> Unit = {}): Short = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Short.requireNonDefault(): Short = requireNonDefault("cannot be ${defaultShort()}")

fun Int.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultInt()}"), elseBlock: () -> Unit = {}): Int = require({ this != defaultInt() }, throwable, { run(elseBlock) })
fun Int.requireNonDefault(message: String = "cannot be ${defaultInt()}", elseBlock: () -> Unit = {}): Int = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Int.requireNonDefault(): Int = requireNonDefault("cannot be ${defaultInt()}")

fun Long.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultLong()}"), elseBlock: () -> Unit = {}): Long = require({ this != defaultLong() }, throwable, { run(elseBlock) })
fun Long.requireNonDefault(message: String = "cannot be ${defaultLong()}", elseBlock: () -> Unit = {}): Long = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Long.requireNonDefault(): Long = requireNonDefault("cannot be ${defaultLong()}")

fun Float.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultFloat()}"), elseBlock: () -> Unit = {}): Float = require({ this != defaultFloat() }, throwable, { run(elseBlock) })
fun Float.requireNonDefault(message: String = "cannot be ${defaultFloat()}", elseBlock: () -> Unit = {}): Float = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Float.requireNonDefault(): Float = requireNonDefault("cannot be ${defaultFloat()}")

fun Double.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultDouble()}"), elseBlock: () -> Unit = {}): Double = require({ this != defaultDouble() }, throwable, { run(elseBlock) })
fun Double.requireNonDefault(message: String = "cannot be ${defaultDouble()}", elseBlock: () -> Unit = {}): Double = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Double.requireNonDefault(): Double = requireNonDefault("cannot be ${defaultDouble()}")

fun String.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultString()}"), elseBlock: () -> Unit = {}): String = require({ this != defaultString() }, throwable, { run(elseBlock) })
fun String.requireNonDefault(message: String = "cannot be ${defaultString()}", elseBlock: () -> Unit = {}): String = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun String.requireNonDefault(): String = requireNonDefault("cannot be ${defaultString()}")

fun BigInteger.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultBigInteger()}"), elseBlock: () -> Unit = {}): BigInteger = require({ this != defaultBigInteger() }, throwable, { run(elseBlock) })
fun BigInteger.requireNonDefault(message: String = "cannot be ${defaultBigInteger()}", elseBlock: () -> Unit = {}): BigInteger = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun BigInteger.requireNonDefault(): BigInteger = requireNonDefault("cannot be ${defaultBigInteger()}")

fun BigDecimal.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultBigDecimal()}"), elseBlock: () -> Unit = {}): BigDecimal = require({ this != defaultBigDecimal() }, throwable, { run(elseBlock) })
fun BigDecimal.requireNonDefault(message: String = "cannot be ${defaultBigDecimal()}", elseBlock: () -> Unit = {}): BigDecimal = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun BigDecimal.requireNonDefault(): BigDecimal = requireNonDefault("cannot be ${defaultBigDecimal()}")