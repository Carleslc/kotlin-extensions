package me.carleslc.kotlin.extensions.collections

import java.util.Random

public fun <T> Collection<T>.init(): Collection<T> = take(size - 1)

public fun <T : Any> Iterable<T?>.trimNulls(): List<T> = filterNotNull()

public fun Iterable<String?>.trim(): List<String> = filterNotNull().filterNot { it.isBlank() }

public fun <T1, T2> Iterable<T1>.combine(other: Iterable<T2>): List<Pair<T1, T2>> = combine(other, { thisItem: T1, otherItem: T2 -> Pair(thisItem, otherItem) })

public fun <T1, T2, R> Iterable<T1>.combine(other: Iterable<T2>, transform: (thisItem: T1, otherItem:T2) -> R): List<R>
        = flatMap { thisItem -> other.map { otherItem -> transform(thisItem, otherItem) } }

public inline fun <T> Int.timesToListOf(predicate: (Int) -> T) = (0..this - 1).map { predicate(it) }

public fun randomIntList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextInt() }

public fun randomIntList(size: Int, bound: Int, generator: Random = Random()) = size.timesToListOf { generator.nextInt(bound) }

public fun randomFloatList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextFloat() }

public fun randomDoubleList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextDouble() }

public fun randomBooleanList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextBoolean() }
