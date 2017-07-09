@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.preconditions

import me.carleslc.kotlin.extensions.standard.andThrow
import me.carleslc.kotlin.extensions.standard.with

inline fun <T> T.require(requirement: T.() -> Boolean, noinline throwable: T.() -> Throwable = { IllegalArgumentException("$this does not match requirements") }, noinline elseBlock: T.() -> Unit = {}): T = if (run(requirement)) this else { run(elseBlock.with(this).andThrow(throwable())) }

inline fun <T> T.require(requirement: T.() -> Boolean, throwable: Throwable = IllegalArgumentException("$this does not match requirements"), noinline elseBlock: T.() -> Unit = {}): T = require(requirement, { throwable }, elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean, message: String = "$this does not match requirements", noinline elseBlock: T.() -> Unit = {}): T = require(requirement, IllegalArgumentException(message), elseBlock)
inline fun <T> T.require(requirement: T.() -> Boolean): T = require(requirement, "$this does not match requirements")

inline fun <T> T?.requireNotNull(throwable: Throwable = IllegalArgumentException("cannot be null"), noinline elseBlock: () -> Unit = {}): T = if (this != null) this else { run(elseBlock.andThrow(throwable)) }

inline fun <T> T?.requireNotNull(message: String = "cannot be null", noinline elseBlock: () -> Unit = {}): T = requireNotNull(IllegalArgumentException(message), elseBlock)
inline fun <T> T?.requireNotNull(): T = requireNotNull("cannot be null")

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
