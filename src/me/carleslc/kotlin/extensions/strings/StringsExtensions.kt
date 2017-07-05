package me.carleslc.kotlin.extensions.strings

import me.carleslc.kotlin.extensions.standard.letOrElse

public inline fun <T> T.toString(transform: (T) -> String) = let(transform)

public inline fun <T> T?.toString(nullString: String = null.toString(), transform: (T) -> String) = letOrElse(nullString, transform)

public fun <T> T?.wrap() = "$this"

public fun <T> T?.wrapString() = if (this is CharSequence) wrap() else toString()

public fun concat(vararg params: Any?) = params.joinToString(" ")

public fun concatWith(separator: String = " ", vararg params: Any?) = params.joinToString(separator)
