@file:Suppress("NOTHING_TO_INLINE")
package me.carleslc.kotlin.extensions.conversions

import me.carleslc.kotlin.extensions.standard.getOrNull
import org.funktionale.tries.Try

inline fun Long.toBoolean() = when (this) {
    0L -> false
    else -> true
}

inline fun Boolean.toLong() = when (this) {
    false -> 0L
    true -> -1L // All bits to 1, supports bitwise not to be false
}

inline fun Double.toBoolean() = when (this) {
    0.0 -> false
    else -> true
}

inline fun Boolean.toDouble() = when (this) {
    false -> 0.0
    true -> -1.0
}

inline fun Byte.toBoolean() = toLong().toBoolean()
inline fun Boolean.toByte() = toLong().toByte()

inline fun Short.toBoolean() = toLong().toShort()
inline fun Boolean.toShort() = toLong().toByte()

inline fun Int.toBoolean() = toLong().toBoolean()
inline fun Boolean.toInt() = toLong().toInt()

inline fun Float.toBoolean() = toDouble().toBoolean()
inline fun Boolean.toFloat() = toDouble().toFloat()

inline fun Double.toBits(): Long = java.lang.Double.doubleToRawLongBits(this)
inline fun Float.toBits(): Int = java.lang.Float.floatToRawIntBits(this)

inline fun Int.toBinaryString(): String = java.lang.Integer.toBinaryString(this)
inline fun Long.toBinaryString(): String = java.lang.Long.toBinaryString(this)
inline fun Double.toBinaryString(): String = toBits().toBinaryString()
inline fun Float.toBinaryString(): String = toBits().toBinaryString()

inline fun String.toByteOrNull(): Byte? = Try { java.lang.Byte.parseByte(this) }.getOrNull()
inline fun String.toByteOrRun(crossinline defaultBlock: (String) -> Byte): Byte = toByteOrNull() ?: run(defaultBlock)
inline fun String.toByteOrDefault(default: Byte = 0): Byte = toByteOrRun { default }

inline fun String.toShortOrNull(): Short? = Try { java.lang.Short.parseShort(this) }.getOrNull()
inline fun String.toShortOrRun(crossinline defaultBlock: (String) -> Short): Short = toShortOrNull() ?: run(defaultBlock)
inline fun String.toShortOrDefault(default: Short = 0): Short = toShortOrRun { default }

inline fun String.toIntOrNull(): Int? = Try { java.lang.Integer.parseInt(this) }.getOrNull()
inline fun String.toIntOrRun(crossinline defaultBlock: (String) -> Int): Int = toIntOrNull() ?: run(defaultBlock)
inline fun String.toIntOrDefault(default: Int = 0): Int = toIntOrRun { default }

inline fun String.toLongOrNull(): Long? = Try { java.lang.Long.parseLong(this) }.getOrNull()
inline fun String.toLongOrRun(crossinline defaultBlock: (String) -> Long): Long = toLongOrNull() ?: run(defaultBlock)
inline fun String.toLongOrDefault(default: Long = 0): Long = toLongOrRun { default }

inline fun String.toFloatOrNull(): Float? = Try { java.lang.Float.parseFloat(this) }.getOrNull()
inline fun String.toFloatOrRun(crossinline defaultBlock: (String) -> Float): Float = toFloatOrNull() ?: run(defaultBlock)
inline fun String.toFloatOrDefault(default: Float = 0f): Float = toFloatOrRun { default }

inline fun String.toDoubleOrNull(): Double? = Try { java.lang.Double.parseDouble(this) }.getOrNull()
inline fun String.toDoubleOrRun(crossinline defaultBlock: (String) -> Double): Double = toDoubleOrNull() ?: run(defaultBlock)
inline fun String.toDoubleOrDefault(default: Double = 0.0): Double = toDoubleOrRun { default }

inline fun String.toBooleanOrNull(): Boolean? = if (equals("true", ignoreCase = true)) true else if (equals("false", ignoreCase = true)) false else null
inline fun String.toBooleanOrRun(defaultBlock: (String) -> Boolean): Boolean = toBooleanOrNull() ?: run(defaultBlock)
inline fun String.toBooleanOrDefault(default: Boolean = false): Boolean = toBooleanOrRun { default }

inline fun Any?.toByte(): Byte {
    return when (this) {
        null -> 0
        is Number -> toByte()
        else -> toString().toByteOrDefault()
    }
}

inline fun Any?.toShort(): Short {
    return when (this) {
        null -> 0
        is Number -> toShort()
        else -> toString().toShortOrDefault()
    }
}

inline fun Any?.toInt(): Int {
    return when (this) {
        null -> 0
        is Number -> toInt()
        else -> toString().toIntOrDefault()
    }
}

inline fun Any?.toLong(): Long {
    return when (this) {
        null -> 0L
        is Number -> toLong()
        else -> toString().toLongOrDefault()
    }
}

inline fun Any?.toFloat(): Float {
    return when (this) {
        null -> 0f
        is Number -> toFloat()
        else -> toString().toFloatOrDefault()
    }
}

inline fun Any?.toDouble(): Double {
    return when (this) {
        null -> 0.0
        is Number -> toDouble()
        else -> toString().toDoubleOrDefault()
    }
}

inline fun Any?.toBoolean(): Boolean {
    return when (this) {
        null -> false
        is Boolean -> this
        is Double -> toBoolean()
        is Float -> toBoolean()
        is Number -> toLong().toBoolean()
        else -> toString().toBooleanOrDefault()
    }
}
