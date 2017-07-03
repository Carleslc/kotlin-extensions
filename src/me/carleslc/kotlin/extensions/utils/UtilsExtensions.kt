package me.carleslc.kotlin.extensions.utils

public inline fun <T> Int.timesIndexed(predicate: (Int) -> T) = (0..this - 1).forEach { predicate(it) }

public inline fun <T> Int.times(predicate: () -> T) = timesIndexed { predicate() }
