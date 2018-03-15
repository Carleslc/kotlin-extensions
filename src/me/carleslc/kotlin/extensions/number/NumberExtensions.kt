@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.number

import com.google.common.math.DoubleMath
import com.google.common.math.LongMath
import me.carleslc.kotlin.extensions.strings.toFixed
import java.math.BigDecimal
import java.math.RoundingMode

inline fun Long.even() = this % 2L == 0L
inline fun Number.even() = toLong().even()

inline fun Long.odd() = !even()
inline fun Number.odd() = toLong().odd()

inline fun Number.isInteger() = when (this) {
    is Double -> DoubleMath.isMathematicalInteger(this)
    is Float -> DoubleMath.isMathematicalInteger(toDouble())
    is BigDecimal -> signum() == 0 || scale() <= 0 || stripTrailingZeros().scale() <= 0
    else -> true
}

inline fun Double.floor(): Long = Math.floor(this).toLong()
inline fun Double.ceil(): Long = Math.ceil(this).toLong()

inline fun Float.floor(): Long = toDouble().floor()
inline fun Float.ceil(): Long = toDouble().ceil()

inline fun Double.clamp(min: Double, max: Double): Double = Math.max(min, Math.min(this, max))
inline fun Float.clamp(min: Float, max: Float): Float = Math.max(min, Math.min(this, max))
inline fun Long.clamp(min: Long, max: Long): Long = Math.max(min, Math.min(this, max))
inline fun Int.clamp(min: Int, max: Int): Int = Math.max(min, Math.min(this, max))
inline fun Short.clamp(min: Short, max: Short): Short = toLong().clamp(min.toLong(), max.toLong()).toShort()
inline fun Byte.clamp(min: Byte, max: Byte): Byte = toLong().clamp(min.toLong(), max.toLong()).toByte()
inline fun Number.clamp(min: Number, max: Number): Long = toLong().clamp(min.toLong(), max.toLong())

inline infix fun Double.pow(exp: Double): Double = Math.pow(this, exp)
inline infix fun Float.pow(exp: Float): Double = toDouble() pow exp.toDouble()
inline infix fun Long.pow(exp: Int): Long = LongMath.pow(this, exp)
inline infix fun Int.pow(exp: Int): Long = toLong() pow exp
inline infix fun Short.pow(exp: Short): Long = toInt() pow exp.toInt()
inline infix fun Byte.pow(exp: Byte): Long = toInt() pow exp.toInt()
inline infix fun Number.pow(exp: Int): Long = toLong() pow exp

inline fun Double.abs(): Double = Math.abs(this)
inline fun Long.abs(): Long = Math.abs(this)
inline fun Int.abs(): Int = Math.abs(this)
inline fun Number.abs(): Int = Math.abs(toInt())

inline fun gcd(a: Long, b: Long): Long = LongMath.gcd(a, b)
inline fun gcd(a: Byte, b: Byte): Byte = gcd(a.toLong(), b.toLong()).toByte()
inline fun gcd(a: Short, b: Short): Short = gcd(a.toLong(), b.toLong()).toShort()
inline fun gcd(a: Int, b: Int): Int = gcd(a.toLong(), b.toLong()).toInt()

inline fun lcm(a: Long, b: Long) = a * b / gcd(a, b)
inline fun lcm(a: Byte, b: Byte): Byte = lcm(a.toLong(), b.toLong()).toByte()
inline fun lcm(a: Short, b: Short): Short = lcm(a.toLong(), b.toLong()).toShort()
inline fun lcm(a: Int, b: Int): Int = lcm(a.toLong(), b.toLong()).toInt()

inline fun Double.roundToLong(): Long = Math.round(this)
inline fun Float.roundToInt(): Int = Math.round(this)

inline infix fun Number.fdiv(y: Number): Float = toFloat() / y.toFloat()
inline infix fun Number.ddiv(y: Number): Double = toDouble() / y.toDouble()

inline infix fun Number.roundDiv(y: Number): Long = Math.round(ddiv(y))

inline fun Double.sqrt() = Math.sqrt(this)
inline fun Number.sqrt() = toDouble().sqrt()

inline fun Long.sqrt(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Long = LongMath.sqrt(this, roundingMode)
inline fun Number.sqrt(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Long = toLong().sqrt(roundingMode)

inline fun Long.isPowerOfTwo() = LongMath.isPowerOfTwo(this)
inline fun Number.isPowerOfTwo() = toLong().isPowerOfTwo()

inline fun Number.isPrime() = LongMath.isPrime(toLong())

inline fun Double.round(digits: Int = 2): String = toFixed(digits).trimEnd('0')
inline fun Float.round(digits: Int = 2): String = toDouble().round(digits)

inline fun Double.sin(): Double = Math.sin(this)
inline fun Float.sin(): Float = toDouble().sin().toFloat()

inline fun Double.cos(): Double = Math.cos(this)
inline fun Float.cos(): Float = toDouble().cos().toFloat()

inline fun Double.tan(): Double = Math.tan(this)
inline fun Float.tan(): Float = toDouble().tan().toFloat()

inline fun Double.asin(): Double = Math.asin(this)
inline fun Float.asin(): Float = toDouble().asin().toFloat()

inline fun Double.acos(): Double = Math.acos(this)
inline fun Float.acos(): Float = toDouble().acos().toFloat()

inline fun Double.atan(): Double = Math.atan(this)
inline fun Float.atan(): Float = toDouble().atan().toFloat()

inline fun Double.toRadians(): Double = Math.toRadians(this)
inline fun Float.toRadians(): Float = toDouble().toRadians().toFloat()

inline fun Double.toDegrees(): Double = Math.toDegrees(this)
inline fun Float.toDegrees(): Float = toDouble().toDegrees().toFloat()

inline fun Double.exp(): Double = Math.exp(this)
inline fun Float.exp(): Float = toDouble().exp().toFloat()

inline fun Double.log(): Double = Math.log(this)
inline fun Float.log(): Float = toDouble().log().toFloat()
inline fun Double.ln(): Double = log()
inline fun Float.ln(): Float = log()
inline fun Number.log(): Double = toDouble().log()
inline fun Number.ln(): Double = toDouble().log()

inline fun Double.log10(): Double = Math.log10(this)
inline fun Float.log10(): Float = toDouble().log10().toFloat()
inline fun Number.log10(): Double = toDouble().log10()

inline fun Long.log10(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = LongMath.log10(this, roundingMode)
inline fun Number.log10(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = LongMath.log10(toLong(), roundingMode)
inline fun Long.log2(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = LongMath.log2(this, roundingMode)
inline fun Double.log2(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = DoubleMath.log2(this, roundingMode)
inline fun Number.log2(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = toDouble().log2(roundingMode)

inline fun Double.cbrt(): Double = Math.cbrt(this)
inline fun Float.cbrt(): Float = toDouble().cbrt().toFloat()

inline infix fun Double.IEEEremainder(divisor: Double): Double = Math.IEEEremainder(this, divisor)
inline infix fun Float.IEEEremainder(divisor: Double): Float = toDouble().IEEEremainder(divisor).toFloat()

inline fun Double.rint(): Double = Math.rint(this)
inline fun Float.rint(): Float = toDouble().rint().toFloat()

inline fun Double.atan2(x: Double): Double = Math.atan2(this, x)
inline fun Float.atan2(x: Float): Float = toDouble().atan2(x.toDouble()).toFloat()

inline fun Double.ulp(): Double = Math.ulp(this)
inline fun Float.ulp(): Float = Math.ulp(toDouble()).toFloat()

inline fun Double.signum(): Double = Math.signum(this)
inline fun Float.signum(): Float = Math.signum(toDouble()).toFloat()

inline fun Double.sinh(): Double = Math.sinh(this)
inline fun Float.sinh(): Float = Math.sinh(toDouble()).toFloat()

inline fun Double.cosh(): Double = Math.cosh(this)
inline fun Float.cosh(): Float = Math.cosh(toDouble()).toFloat()

inline fun Double.tanh(): Double = Math.tanh(this)
inline fun Float.tanh(): Float = Math.tanh(toDouble()).toFloat()

inline fun Double.expm1(): Double = Math.expm1(this)
inline fun Float.expm1(): Float = Math.expm1(toDouble()).toFloat()

inline fun Double.log1p(): Double = Math.log1p(this)
inline fun Float.log1p(): Float = Math.log1p(toDouble()).toFloat()

inline infix fun Double.copySign(sign: Double): Double = Math.copySign(this, sign)
inline infix fun Float.copySign(sign: Float): Float = Math.copySign(this, sign)

inline fun Double.exponent(): Int = Math.getExponent(this)
inline fun Float.exponent(): Int = Math.getExponent(this)

inline fun Double.next(direction: Double = Double.POSITIVE_INFINITY): Double = Math.nextAfter(this, direction)
inline fun Float.next(direction: Double = Double.POSITIVE_INFINITY): Float = Math.nextAfter(this, direction)

inline fun Double.nextUp(): Double = Math.nextUp(this)
inline fun Float.nextUp(): Float = Math.nextUp(this)

inline fun Double.nextDown(): Double = Math.nextDown(this)
inline fun Float.nextDown(): Float = Math.nextDown(this)

inline infix fun Double.scalb(scaleFactor: Int): Double = Math.scalb(this, scaleFactor)
inline infix fun Float.scalb(scaleFactor: Int): Float = Math.scalb(this, scaleFactor)
