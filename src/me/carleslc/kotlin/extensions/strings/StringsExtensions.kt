package me.carleslc.kotlin.extensions.strings

import me.carleslc.kotlin.extensions.standard.letOr

public inline fun <T> T?.toString(nullString: String = null.toString(), transform: (T) -> String) = letOr(nullString, transform)

public fun concat(vararg params: Any?) = params.joinToString(" ")

public fun concatWith(separator: String = " ", vararg params: Any?) = params.joinToString(separator)
