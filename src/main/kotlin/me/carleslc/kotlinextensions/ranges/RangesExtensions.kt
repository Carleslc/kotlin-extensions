package me.carleslc.kotlinextensions.ranges

import kotlin.math.abs

fun CharRange.size() = first - last + 1
fun IntRange.size() = abs(first) - last + 1
fun LongRange.size() = last- first + 1
fun ULongRange.size() = first - last + 1UL
fun UIntRange.size() = first - last + 1.toUInt()
