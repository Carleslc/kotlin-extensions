package me.carleslc.kotlin.extensions.utils

public inline fun <T> Int.timesIndexed(predicate: (Int) -> T) = (1..this).forEach { predicate(it) }

public inline fun <T> Int.times(predicate: () -> T) = timesIndexed { predicate() }
