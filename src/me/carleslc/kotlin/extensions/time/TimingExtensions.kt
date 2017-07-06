package me.carleslc.kotlin.extensions.time

import me.carleslc.kotlin.extensions.number.roundDiv
import me.carleslc.kotlin.extensions.number.zero
import me.carleslc.kotlin.extensions.standard.with
import java.io.PrintStream
import java.time.Duration
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

    fun from(time: Long, unit: TimeUnit): Duration = Duration.ofNanos(nanos() - unit.toNanos(time))

    fun fromMillis(millis: Long): Duration = from(millis, TimeUnit.MILLISECONDS)

    fun fromNanos(nanos: Long): Duration = from(nanos, TimeUnit.NANOSECONDS)

}

public fun measure(block: () -> Unit): Duration = measureNanoTime(block).nanoseconds

public fun measureAndPrint(limit: TimeUnit = TimeUnit.NANOSECONDS,
                           formatter: TimeUnitFormatter = TimeUnitFormatter.SHORT,
                           transformation: ((String) -> String)? = null,
                           outputStream: PrintStream = System.out,
                           block: () -> Unit) = measure(block).humanize(limit, formatter, transformation).run(outputStream::println)

public fun Duration.humanize(limit: TimeUnit = TimeUnit.NANOSECONDS, formatter: TimeUnitFormatter = TimeUnitFormatter.SHORT, transformation: ((String) -> String)? = null): String {
    val builder = StringBuilder()
    var nanos: Long = toNanos()
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
