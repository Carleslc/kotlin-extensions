@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.html

import me.carleslc.kotlin.extensions.map.joinToString
import me.carleslc.kotlin.extensions.tuples.joinToString

typealias Attributes = Map<String, String>

inline fun attr(): Attributes = emptyMap()

inline fun attr(attribute: Pair<String, String>): Attributes = java.util.Collections.singletonMap(attribute.first, attribute.second)

inline fun attr(vararg attributes: Pair<String, String>): Attributes = mapOf(*attributes)

inline fun String.tag(tag: String) = "<$tag>$this</$tag>"

inline fun String.tag(tag: String, attributes: Attributes) = "<$tag${ attributes.joinToString(separator = " ", prefix = " ", postfix = "", transform = { it.joinToString("=") }).trim() }>$this</$tag>"

inline fun String.h(size: Int = 1, attributes: Attributes = attr()) = tag("h$size", attributes)

inline fun String.span(attributes: Attributes = attr()) = tag("span", attributes)

inline fun String.span(style: String) = span(attr("style" to style))

inline fun String.a(attributes: Attributes = attr()) = tag("a", attributes)

inline fun String.href(url: String = "", attributes: Attributes = attr()) = a(attr("href" to url) + attributes)
