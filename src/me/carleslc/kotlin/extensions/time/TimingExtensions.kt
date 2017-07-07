package me.carleslc.kotlin.extensions.time

import me.carleslc.kotlin.extensions.number.roundDiv
import me.carleslc.kotlin.extensions.number.zero
import me.carleslc.kotlin.extensions.standard.with
import java.io.PrintStream
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

private val NANO_MICRO by lazy { 1000.toDouble() }
private val NANO_MILLI by lazy { 1000 * NANO_MICRO }
private val NANO_SECOND by lazy { 1000 * NANO_MILLI }
private val NANO_MINUTE by lazy { 60 * NANO_SECOND }
private val NANO_HOUR by lazy { 60 * NANO_MINUTE }
private val NANO_DAY by lazy { 24 * NANO_HOUR }

public fun millis() = System.currentTimeMillis()

public fun nanos() = System.nanoTime()

public object Durations {

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
    fun fromMillis(millis: Long) = Duration.ofMillis(millis() - millis)

    /** Use this method only if [nanos] is extracted from [nanos()][me.carleslc.kotlin.extensions.time.nanos], otherwise resulting duration will be unexpected */
    fun fromSystemNanos(nanos: Long): Duration = betweenNanos(nanos, nanos())

}

public fun measure(block: () -> Unit): Duration = measureNanoTime(block).nanoseconds

public fun measureAndPrint(limit: TimeUnit = TimeUnit.NANOSECONDS,
                           formatter: TimeUnitFormatter = TimeUnitFormatter.SHORT,
                           transformation: ((String) -> String)? = null,
                           outputStream: PrintStream = System.out,
                           block: () -> Unit) = measure(block).humanize(limit, formatter, transformation).run(outputStream::println)

public fun Duration.humanize(limit: TimeUnit = TimeUnit.NANOSECONDS, formatter: TimeUnitFormatter = TimeUnitFormatter.SHORT, transformation: ((String) -> String)? = null): String {
    val builder = StringBuilder()
    var nanos = abs().toNanos()
    var finished = false

    fun intermediateAppend(threshold: Double, unit: TimeUnit) {
        if (nanos > threshold) {
            val value = if (threshold > zero()) nanos roundDiv threshold else nanos
            builder.append(value).append(" ${ formatter.get(value, unit) } ")
            nanos %= threshold.toLong()
        }
    }

    fun lastAppend(threshold: Double, unit: TimeUnit) {
        if (nanos > threshold || builder.isEmpty()) {
            val value = if (threshold > zero()) nanos roundDiv threshold else nanos
            builder.append(value).append(" ${ formatter.get(value, unit) }")
        }
        finished = true
    }

    fun append(step: TimeUnit, threshold: Double) {
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
    append(TimeUnit.NANOSECONDS, zero())

    return builder.toString().trim().with(transformation)
}
