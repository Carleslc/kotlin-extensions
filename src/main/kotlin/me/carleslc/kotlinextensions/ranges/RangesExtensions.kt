package me.carleslc.kotlinextensions.ranges

import kotlin.math.abs

inline val CharRange.size: Int get() = last - first + 1
inline val IntRange.size: Int get() = last - first + 1
inline val LongRange.size: Long get() = last - first + 1
inline val ULongRange.size: ULong get() = last - first + 1.toULong()
inline val UIntRange.size: UInt get() = last - first + 1.toUInt()
