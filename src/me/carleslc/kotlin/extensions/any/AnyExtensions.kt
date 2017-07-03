package me.carleslc.kotlin.extensions.any

public fun <E, T> E.transform(transform: (E) -> T) = transform(this)

public fun <T> T?.toString(transform: (T) -> String) = this?.let(transform) ?: "null"
