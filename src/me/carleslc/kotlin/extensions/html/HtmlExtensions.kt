package me.carleslc.kotlin.extensions.html

import me.carleslc.kotlin.extensions.map.joinToString
import me.carleslc.kotlin.extensions.tuples.joinToString

typealias Attributes = Map<String, String>

public fun attr(): Attributes = emptyMap()

public fun attr(attribute: Pair<String, String>): Attributes = java.util.Collections.singletonMap(attribute.first, attribute.second)

public fun attr(vararg attributes: Pair<String, String>): Attributes = mapOf(*attributes)

public fun String.tag(tag: String) = "<$tag>$this</$tag>"

public fun String.tag(tag: String, attributes: Attributes) = "<$tag${attributes.joinToString(separator = " ", prefix = " ", postfix = "", transform = { it.joinToString("=") })}>$this</$tag>"

public fun String.h(size: Int = 1, attributes: Attributes = attr()) = tag("h$size", attributes)

public fun String.span(attributes: Attributes = attr()) = tag("span", attributes)

public fun String.span(style: String) = span(attr("style" to style))

public fun String.a(attributes: Attributes = attr()) = tag("a", attributes)

public fun String.href(url: String = "", attributes: Attributes = attr()) = a(attr("href" to url) + attributes)
