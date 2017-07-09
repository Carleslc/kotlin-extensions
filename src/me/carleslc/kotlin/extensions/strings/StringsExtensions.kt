@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.strings

import com.cesarferreira.pluralize.pluralize
import com.cesarferreira.pluralize.singularize
import me.carleslc.kotlin.extensions.standard.letOrElse

inline fun <T> T.toString(transform: (T) -> String) = let(transform)

inline fun <T> T?.toString(nullString: String = null.toString(), transform: (T) -> String) = letOrElse(nullString, transform)

inline fun String.pluralize() = pluralize()
inline fun String.pluralize(count: Int) = pluralize(count)

inline fun String.singularize() = singularize()
inline fun String.singularize(count: Int) = singularize(count)

inline fun <T> T?.wrap() = "$this"

inline fun <T> T?.wrapString() = if (this is CharSequence) wrap() else toString()

inline fun concat(vararg params: Any?) = params.joinToString("")

inline fun join(vararg params: Any?) = params.joinToString(" ")

inline fun joinWith(separator: String = " ", vararg params: Any?) = params.joinToString(separator)

inline fun String.replace(ignoreCase: Boolean = false, vararg vars: Pair<String, String>): String {
    var copy = this
    vars.forEach { copy = copy.replace(it.first, it.second, ignoreCase) }
    return copy
}
