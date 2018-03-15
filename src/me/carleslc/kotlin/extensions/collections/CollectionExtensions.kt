@file:Suppress("NOTHING_TO_INLINE", "UNUSED_PARAMETER")

package me.carleslc.kotlin.extensions.collections

import java.util.Random
import java.util.Collections

object L {
    inline operator fun <reified T> get(vararg ts: T) = if (ts.isNotEmpty()) ts.asList() else emptyList()
}

object S {
    inline operator fun <reified T> get(vararg ts: T) = if (ts.isNotEmpty()) ts.toSet() else emptySet()
}

inline fun <T> Collection<T>?.isBlank(): Boolean = this == null || isEmpty()

inline fun <T> Collection<T?>.anyNull(): Boolean = any { it == null }

inline fun <T> Collection<T?>.allNull(): Boolean = all { it == null }

inline fun <T> Collection<T?>.countNulls(): Int = count { it == null }
inline fun <T> Collection<T?>.countNonNulls(): Int = size - countNulls()

inline fun <T : Any> Iterable<T?>.trimNulls(): List<T> = filterNotNull()
inline fun <T : Any> Iterable<T?>.trimNullsToMutableList(): MutableList<T> = filterNotNullTo(mutableListOf())

inline fun Iterable<String?>.trim(): List<String> = trimNulls().filterNot { it.isBlank() }
inline fun Iterable<String?>.trimToMutableList(): MutableList<String> = trimNulls().filterNotTo(mutableListOf()) { it.isBlank() }

inline fun <T1, T2> Iterable<T1>.combine(other: Iterable<T2>): List<Pair<T1, T2>> = combine(other, { thisItem: T1, otherItem: T2 -> Pair(thisItem, otherItem) })
inline fun <T1, T2> Iterable<T1>.combineToMutableList(other: Iterable<T2>): MutableList<Pair<T1, T2>> = combineToMutableList(other, { thisItem: T1, otherItem: T2 -> Pair(thisItem, otherItem) })

inline fun <T1, T2, R> Iterable<T1>.combine(other: Iterable<T2>, transform: (thisItem: T1, otherItem: T2) -> R): List<R>
        = flatMap { thisItem -> other.map { otherItem -> transform(thisItem, otherItem) } }

inline fun <T1, T2, R> Iterable<T1>.combineToMutableList(other: Iterable<T2>, transform: (thisItem: T1, otherItem: T2) -> R): MutableList<R>
        = flatMapTo(mutableListOf()) { thisItem -> other.map { otherItem -> transform(thisItem, otherItem) } }

inline fun <T, R> Iterable<T>.mapToMutableList(transform: (T) -> R): MutableList<R> = mapTo(mutableListOf(), transform)
inline fun <T, R> Iterable<T>.flatMapToMutableList(transform: (T) -> Iterable<R>): MutableList<R> = flatMapTo(mutableListOf(), transform)

inline fun <T> Int.timesToListOf(predicate: (Int) -> T): List<T> = (0 until this).map { predicate(it) }
inline fun <T> Int.timesToMutableListOf(predicate: (Int) -> T): MutableList<T> = (0..this - 1).mapToMutableList { predicate(it) }

fun <T> MutableList<T>.swap(i: Int, j: Int): MutableList<T> {
    return apply {
        val aux = this[i]
        this[i] = this[j]
        this[j] = aux
    }
}

fun <T> List<T>.swapped(i: Int, j: Int): List<T> = toMutableList().swap(i, j)

inline fun <T> List<T>.getRandom(generator: Random = Random()): T = get(generator.nextInt(size))

inline fun <T> MutableList<T>.shuffle(generator: Random = Random()): MutableList<T> = apply { Collections.shuffle(this, generator) }

inline fun <T> List<T>.shuffled(generator: Random = Random()): List<T> = toMutableList().shuffle()

inline fun randomIntList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextInt() }
inline fun randomIntList(size: Int, bound: Int, generator: Random = Random()) = size.timesToListOf { generator.nextInt(bound) }
inline fun randomFloatList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextFloat() }
inline fun randomDoubleList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextDouble() }
inline fun randomBooleanList(size: Int, generator: Random = Random()) = size.timesToListOf { generator.nextBoolean() }

inline fun <T> List<T>.encapsulate(): List<List<T>> = map { listOf(it) }
inline fun <T> List<T>.encapsulateToMutableList(): MutableList<MutableList<T>> = mapToMutableList { mutableListOf(it) }

inline fun <T> List<List<T>>.concat(): List<T> = fold(listOf()) { acc, l -> acc + l }
inline fun <T> List<List<T>>.concatToMutableList(): MutableList<T> = concat().toMutableList()

inline fun <T> Collection<T>.init(): List<T> = take(size - 1)

inline val Collection<*>.half: Int get() = size / 2

inline fun <T> Collection<T>.firstHalf(): List<T> = take(half)
inline fun <T> Collection<T>.secondHalf(): List<T> = drop(half)

inline fun <T> Collection<T>.split(index: Int): Pair<List<T>, List<T>> = take(index) to drop(index)
inline fun <T> Collection<T>.split(): Pair<List<T>, List<T>> = split(half)
