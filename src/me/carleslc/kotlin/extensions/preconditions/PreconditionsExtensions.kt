@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.preconditions

import me.carleslc.kotlin.extensions.standard.*
import java.math.BigDecimal
import java.math.BigInteger

inline fun <T> T.require(requirement: T.() -> Boolean, noinline throwable: T.() -> Throwable = { IllegalArgumentException("$this does not match requirements") }, noinline elseBlock: T.() -> Unit = {}): T = if (run(requirement)) this else { run(elseBlock.with(this).andThrow(throwable())) }

inline fun <T> T.require(requirement: T.() -> Boolean, throwable: Throwable = IllegalArgumentException("$this does not match requirements"), noinline elseBlock: T.() -> Unit = {}): T = require(requirement, { throwable }, elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean, message: String = "$this does not match requirements", noinline elseBlock: T.() -> Unit = {}): T = require(requirement, IllegalArgumentException(message), elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean): T = require(requirement, "$this does not match requirements")

inline fun <T> T?.requireNotNull(throwable: Throwable = IllegalArgumentException("cannot be null"), noinline elseBlock: () -> Unit = {}): T = if (this != null) this else { run(elseBlock.andThrow(throwable)) }
inline fun <T> T?.requireNotNull(message: String = "cannot be null", noinline elseBlock: () -> Unit = {}): T = requireNotNull(IllegalArgumentException(message), elseBlock)
inline fun <T> T?.requireNotNull(): T = requireNotNull("cannot be null")

inline fun String?.requireNotBlank(throwable: Throwable = IllegalArgumentException("cannot be blank"), noinline elseBlock: () -> Unit = {}): String = if (!isNullOrBlank()) this!! else { run(elseBlock.andThrow(throwable)) }
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

inline fun Byte.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultByte()}"), noinline elseBlock: () -> Unit = {}): Byte = if (this != defaultByte()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Byte.requireNonDefault(message: String = "cannot be ${defaultByte()}", noinline elseBlock: () -> Unit = {}): Byte = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Byte.requireNonDefault(): Byte = requireNonDefault("cannot be ${defaultByte()}")

inline fun Short.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultShort()}"), noinline elseBlock: () -> Unit = {}): Short = if (this != defaultShort()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Short.requireNonDefault(message: String = "cannot be ${defaultShort()}", noinline elseBlock: () -> Unit = {}): Short = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Short.requireNonDefault(): Short = requireNonDefault("cannot be ${defaultShort()}")

inline fun Int.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultInt()}"), noinline elseBlock: () -> Unit = {}): Int = if (this != defaultInt()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Int.requireNonDefault(message: String = "cannot be ${defaultInt()}", noinline elseBlock: () -> Unit = {}): Int = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Int.requireNonDefault(): Int = requireNonDefault("cannot be ${defaultInt()}")

inline fun Long.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultLong()}"), noinline elseBlock: () -> Unit = {}): Long = if (this != defaultLong()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Long.requireNonDefault(message: String = "cannot be ${defaultLong()}", noinline elseBlock: () -> Unit = {}): Long = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Long.requireNonDefault(): Long = requireNonDefault("cannot be ${defaultLong()}")

inline fun Float.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultFloat()}"), noinline elseBlock: () -> Unit = {}): Float = if (this != defaultFloat()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Float.requireNonDefault(message: String = "cannot be ${defaultFloat()}", noinline elseBlock: () -> Unit = {}): Float = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Float.requireNonDefault(): Float = requireNonDefault("cannot be ${defaultFloat()}")

inline fun Double.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultDouble()}"), noinline elseBlock: () -> Unit = {}): Double = if (this != defaultDouble()) this else { run(elseBlock.andThrow(throwable)) }
inline fun Double.requireNonDefault(message: String = "cannot be ${defaultDouble()}", noinline elseBlock: () -> Unit = {}): Double = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun Double.requireNonDefault(): Double = requireNonDefault("cannot be ${defaultDouble()}")

inline fun String.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultString()}"), noinline elseBlock: () -> Unit = {}): String = if (this != defaultString()) this else { run(elseBlock.andThrow(throwable)) }
inline fun String.requireNonDefault(message: String = "cannot be ${defaultString()}", noinline elseBlock: () -> Unit = {}): String = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun String.requireNonDefault(): String = requireNonDefault("cannot be ${defaultString()}")

inline fun BigInteger.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultBigInteger()}"), noinline elseBlock: () -> Unit = {}): BigInteger = if (this != defaultBigInteger()) this else { run(elseBlock.andThrow(throwable)) }
inline fun BigInteger.requireNonDefault(message: String = "cannot be ${defaultBigInteger()}", noinline elseBlock: () -> Unit = {}): BigInteger = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun BigInteger.requireNonDefault(): BigInteger = requireNonDefault("cannot be ${defaultBigInteger()}")

inline fun BigDecimal.requireNonDefault(throwable: Throwable = IllegalArgumentException("cannot be  ${defaultBigDecimal()}"), noinline elseBlock: () -> Unit = {}): BigDecimal = if (this != defaultBigDecimal()) this else { run(elseBlock.andThrow(throwable)) }
inline fun BigDecimal.requireNonDefault(message: String = "cannot be ${defaultBigDecimal()}", noinline elseBlock: () -> Unit = {}): BigDecimal = requireNonDefault(IllegalArgumentException(message), elseBlock)
inline fun BigDecimal.requireNonDefault(): BigDecimal = requireNonDefault("cannot be ${defaultBigDecimal()}")
