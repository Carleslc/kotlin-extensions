package me.carleslc.kotlinextensions.ranges

import java.math.BigInteger

inline val CharRange.size: Int get() = last - first + 1
inline val IntRange.size: Long get() = last.toLong() - first.toLong() + 1L
inline val UIntRange.size: Long get() = last.toLong() - first.toLong() + 1L
inline val LongRange.size: BigInteger get() = BigInteger.valueOf(last) - BigInteger.valueOf(first) + BigInteger.ONE
inline val ULongRange.size: BigInteger get() = BigInteger(last.toString()) - BigInteger(first.toString()) + BigInteger.ONE
