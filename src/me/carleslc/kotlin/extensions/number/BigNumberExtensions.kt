@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.number

import com.google.common.math.BigIntegerMath
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode
import java.util.*

val LOG2 by lazy { Math.log(2.0) }
val TWO_BIG by lazy { 2.toBigInteger() }

inline fun Long.toBigInteger() = BigInteger(toString())
inline fun Number.toBigInteger() = toLong().toBigInteger()

inline fun Double.toBigDecimal(mathContext: MathContext = MathContext.UNLIMITED) = BigDecimal(this, mathContext)
inline fun Number.toBigDecimal(mathContext: MathContext = MathContext.UNLIMITED) = toDouble().toBigDecimal(mathContext)

inline fun BigInteger.even() = mod(2L.toBigInteger()) == BigInteger.ZERO
inline fun BigInteger.odd() = !even()

inline infix fun BigInteger.pow(exp: Int): BigInteger = pow(exp)
inline infix fun BigDecimal.pow(exp: Int): BigDecimal = pow(exp)

inline fun BigInteger.fitsInLong(): Boolean = this in (Long.MIN_VALUE.toBigInteger()..Long.MAX_VALUE.toBigInteger())
inline fun BigInteger.fitsInInt(): Boolean = this in (Int.MIN_VALUE.toBigInteger()..Int.MAX_VALUE.toBigInteger())

inline fun BigDecimal.fitsInDouble(): Boolean = this in (-Double.MAX_VALUE.toBigDecimal()..Double.MAX_VALUE.toBigDecimal())
inline fun BigDecimal.fitsInFloat(): Boolean = this in (-Float.MAX_VALUE.toBigDecimal()..Float.MAX_VALUE.toBigDecimal())

tailrec fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b == BigInteger.ZERO) a else if (a.fitsInLong() && b.fitsInLong()) gcd(a.toLong(), b.toLong()).toBigInteger() else gcd(a, a % b)
inline fun lcm(a: BigInteger, b: BigInteger) = a * b / gcd(a, b)

inline fun BigDecimal.round(precision: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal = round(MathContext(precision, roundingMode))

inline fun MathContext.round(n: BigDecimal): BigDecimal = run(n::round)

inline fun MathContext.roundFunctionBigDecimal(): (BigDecimal) -> BigDecimal = { round(it) }

inline fun Double.round(mathContext: MathContext = MathContext(1)): BigDecimal = run(mathContext::round)
inline fun Float.round(mathContext: MathContext = MathContext(1)): BigDecimal = run(mathContext::round)

inline fun Double.round(precision: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal = round(MathContext(precision, roundingMode))
inline fun Float.round(precision: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal = round(MathContext(precision, roundingMode))

inline fun MathContext.round(n: Double): BigDecimal = run(n.toBigDecimal()::round)
inline fun MathContext.round(n: Float): BigDecimal = run(n.toBigDecimal()::round)

inline fun MathContext.roundFunctionDouble(): (Double) -> BigDecimal = { round(it) }
inline fun MathContext.roundFunctionFloat(): (Float) -> BigDecimal = { round(it) }

inline fun Number.roundDiv(y: Number, mathContext: MathContext): BigDecimal = toBigDecimal().divide(y.toBigDecimal(), mathContext)

inline fun BigInteger.sqrt(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): BigInteger = BigIntegerMath.sqrt(this, roundingMode)
inline fun BigDecimal.sqrt(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): BigInteger = toBigInteger().sqrt(roundingMode)

inline fun BigInteger.isPowerOfTwo() = run(BigIntegerMath::isPowerOfTwo)
inline fun BigDecimal.isPowerOfTwo() = toBigInteger().isPowerOfTwo()

inline fun BigInteger.log10(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = BigIntegerMath.log10(this, roundingMode)
inline fun BigInteger.log2(roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Int = BigIntegerMath.log2(this, roundingMode)
inline fun BigInteger.ln(): Double = log()
inline fun BigInteger.log(): Double {
    var ref = this
    val blex = bitLength() - 1022 // any value in 60..1023 is ok
    if (blex > 0) ref = shiftRight(blex)
    val res = ref.toDouble().log()
    return if (blex > 0) res + blex * LOG2 else res
}

inline fun Int.factorial(): BigInteger = run(BigIntegerMath::factorial)
inline fun Number.factorial(): BigInteger = toInt().factorial()

inline infix fun Int.binomial(k: Int): BigInteger = BigIntegerMath.binomial(this, k)
inline infix fun Number.binomial(k: Int): BigInteger = toInt() binomial k

inline fun Number.fibonacci(): BigInteger = toBigInteger().fibonacci()
fun BigInteger.fibonacci(): BigInteger {
    require(this >= BigInteger.ZERO, { "Cannot compute fibonacci for negative numbers" })
    fun fib(n: BigInteger): Pair<BigInteger, BigInteger> {
        if (n == BigInteger.ZERO) return BigInteger.ZERO to BigInteger.ONE
        val (a, b) = fib(n / TWO_BIG)
        val c = a * (b * TWO_BIG - a)
        val d = a * a + b * b
        return if (n % TWO_BIG == BigInteger.ZERO) c to d else d to c + d
    }
    return fib(this).first
}

inline fun probablePrime(bits: Int, random: Random = Random()) = BigInteger.probablePrime(bits, random)

inline fun BigInteger.isPrime(certainty: Int = 5, precise: Boolean = false): Boolean {
    if (!isProbablePrime(certainty)) return false else if (!precise) return true

    if (TWO_BIG != this && mod(TWO_BIG) == BigInteger.ZERO) return false

    var index = BigInteger.ONE
    val indices = generateSequence { index += TWO_BIG; if (index.multiply(index) <= this) index else null }
    indices.forEach {
        if (mod(it) == BigInteger.ZERO) return false
    }
    return true;
}
