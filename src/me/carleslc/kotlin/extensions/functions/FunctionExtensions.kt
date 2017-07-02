package me.carleslc.kotlin.extensions.functions

public fun <A, B> compose(f: (A) -> A,
		g: (A) -> B): (A) -> B = { x -> g(f(x)) }


public fun <A, B> compose(f: (A) -> A,
		g: (A) -> A,
		h: (A) -> B): (A) -> B = { x -> h(g(f(x))) }
