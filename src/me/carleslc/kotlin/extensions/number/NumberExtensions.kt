package me.carleslc.kotlin.extensions.number

import me.carleslc.kotlin.extensions.standard.andReturn
import org.funktionale.tries.Try

public fun Int.even() = this % 2 == 0

public fun Int.odd() = !even()

// TODO: GCD, LCM, Any?.toInt,Long,Double,Float,Boolean

public fun Int.toBoolean() = when (this) {
    0 -> false
    else -> true
}

public fun Boolean.toInt() = when (this) {
    false -> 0
    true -> -1 // All bits to 1, supports bitwise not to be false
}

public fun Boolean.toDouble() = toInt().toDouble()

public fun Double.toBoolean() = toInt().toBoolean()

public fun zero() = 0.toDouble()

public fun zerof() = 0.toFloat()

public fun one() = 1.toDouble()

public fun onef() = 1.toFloat()

public infix fun Number.fdiv(y: Number): Float = toFloat() / y.toFloat()

public infix fun Number.ddiv(y: Number): Double = toDouble() / y.toDouble()

public infix fun Number.roundDiv(y: Number): Long = Math.round(ddiv(y))

public fun Double.floor(): Long = Math.floor(this).toLong()

public fun Double.ceil(): Long = Math.ceil(this).toLong()

public fun Float.floor(): Long = toDouble().floor()

public fun Float.ceil(): Long = toDouble().ceil()

public fun (() -> Any?).returnInt(): () -> Int = andReturn(0)

public fun (() -> Any?).returnLong(): () -> Long = andReturn(0L)

public fun (() -> Any?).returnFloat(): () -> Float = andReturn(zerof())

public fun (() -> Any?).returnDouble(): () -> Double = andReturn(zero())

public fun String.toIntOrRun(defaultBlock: () -> Int): Int = Try { toInt() }.getOrElse { run(defaultBlock) }

public fun String.toIntOrDefault(default: Int = 0): Int = toIntOrRun { default }

public fun String.toLongOrRun(defaultBlock: () -> Long): Long = Try { toLong() }.getOrElse { run(defaultBlock) }

public fun String.toLongOrDefault(default: Long = 0): Long = toLongOrRun { default }

public fun String.toFloatOrRun(defaultBlock: () -> Float): Float = Try { toFloat() }.getOrElse { run(defaultBlock) }

public fun String.toFloatOrDefault(default: Float = zerof()): Float = toFloatOrRun { default }

public fun String.toDoubleOrRun(defaultBlock: () -> Double): Double = Try { toDouble() }.getOrElse { run(defaultBlock) }

public fun String.toDoubleOrDefault(default: Double = zero()): Double = toDoubleOrRun { default }
