@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlin.extensions.time

import me.carleslc.kotlin.extensions.number.roundDiv
import me.carleslc.kotlin.extensions.standard.with
import java.io.PrintStream
import java.math.MathContext
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

private val NANO_MICRO by lazy { 1000L }
private val NANO_MILLI by lazy { 1000 * NANO_MICRO }
private val NANO_SECOND by lazy { 1000 * NANO_MILLI }
private val NANO_MINUTE by lazy { 60 * NANO_SECOND }
private val NANO_HOUR by lazy { 60 * NANO_MINUTE }
private val NANO_DAY by lazy { 24 * NANO_HOUR }

inline fun millis() = System.currentTimeMillis()

inline fun nanos() = System.nanoTime()

object Durations {

    fun between(start: Long, end: Long, unit: TimeUnit) = betweenNanos(unit.toNanos(start), unit.toNanos(end))

    fun betweenNanos(start: Long, end: Long) = Duration.ofNanos(end - start)

    fun betweenMillis(start: Long, end: Long) = Duration.ofMillis(end - start)

    /**
     * Duration from [ago] to current [millis()][millis], so the precision is of milliseconds even if [unit] == [TimeUnit.NANOSECONDS]
     *
     * For nanosecond precision use [betweenNanos] with a proper start point in nanoseconds, or [fromSystemNanos] if you are measuring from [nanos]
     */
    fun from(ago: Long, unit: TimeUnit) = Duration.ofMillis(millis() - unit.toMillis(ago))

    /** Duration from [ago] to current [millis()][millis] */
    fun fromMillis(ago: Long) = Duration.ofMillis(millis() - ago)

    /** Use this method only if [nanos] is extracted from [nanos()][me.carleslc.kotlin.extensions.time.nanos], otherwise resulting duration will be unexpected */
    fun fromSystemNanos(nanos: Long): Duration = betweenNanos(nanos, nanos())

}

inline fun measure(block: () -> Unit): Duration = measureNanoTime(block).nanoseconds

inline fun <T> measureAndReturn(block: () -> T): Pair<Duration, T> {
    val start = System.nanoTime()
    val ret = block()
    val duration = (System.nanoTime() - start).nanoseconds
    return duration to ret
}

inline fun <T> measureAndPrint(limit: TimeUnit = TimeUnit.NANOSECONDS,
                           formatter: TimeUnitFormatter = TimeUnitFormatter.LONG,
                           roundingLast: MathContext = MathContext(1),
                           noinline transformation: ((String) -> String)? = null,
                           outputStream: PrintStream = System.out,
                           block: () -> T): T {
    measureAndReturn(block).let {
        it.first.humanize(limit, formatter, roundingLast, transformation).run(outputStream::println)
        return it.second
    }
}

fun Duration.humanize(limit: TimeUnit = TimeUnit.NANOSECONDS, formatter: TimeUnitFormatter = TimeUnitFormatter.LONG, roundingLast: MathContext = MathContext(1), transformation: ((String) -> String)? = null): String {
    val builder = StringBuilder()
    var nanos = abs().toNanos()
    var finished = false

    fun intermediateAppend(threshold: Long, unit: TimeUnit) {
        if (nanos > threshold) {
            val value = if (threshold > 0.0) nanos / threshold else nanos
            builder.append(formatter.format(value, unit))
            nanos %= threshold
        }
    }

    fun lastAppend(threshold: Long, unit: TimeUnit) {
        if (nanos > threshold || builder.isEmpty()) {
            val value = if (threshold > 0.0) nanos.roundDiv(threshold, roundingLast).toLong() else nanos
            builder.append(formatter.formatLast(value, unit))
        }
        finished = true
    }

    fun append(step: TimeUnit, threshold: Long) {
        if (!finished) {
            when (step) {
                limit -> lastAppend(threshold, step)
                else -> intermediateAppend(threshold, step)
            }
        }
    }

    append(TimeUnit.DAYS, NANO_DAY)
    append(TimeUnit.HOURS, NANO_HOUR)
    append(TimeUnit.MINUTES, NANO_MINUTE)
    append(TimeUnit.SECONDS, NANO_SECOND)
    append(TimeUnit.MILLISECONDS, NANO_MILLI)
    append(TimeUnit.MICROSECONDS, NANO_MICRO)
    append(TimeUnit.NANOSECONDS, 0L)

    return builder.toString().trim().with(transformation)
}
