package me.carleslc.kotlin.extensions.time

import me.carleslc.kotlin.extensions.number.zero
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.concurrent.TimeUnit

private val NANO_MICRO by lazy { 1000.toDouble() }
private val NANO_MILLI by lazy { 1000 * NANO_MICRO }
private val NANO_SECOND by lazy { 1000 * NANO_MILLI }
private val NANO_MINUTE by lazy { 60 * NANO_SECOND }
private val NANO_HOUR by lazy { 60 * NANO_MINUTE }
private val NANO_DAY by lazy { 24 * NANO_HOUR }

public fun durationFromStart(start: Long, unit: TimeUnit): Duration = Duration.ofNanos(System.nanoTime() - unit.toNanos(start))

public fun durationFromStartMillis(startMillis: Long): Duration = durationFromStart(startMillis, TimeUnit.MILLISECONDS)

public fun durationFromStartNanos(startNanos: Long): Duration = durationFromStart(startNanos, TimeUnit.NANOSECONDS)

public fun Duration.humanize(limit: TimeUnit = TimeUnit.NANOSECONDS): String {
    val builder = StringBuilder()
    var nanos: Long = toNanos()
    var finished = false

    fun intermediateAppend(threshold: Double, unit: String) {
        if (nanos > threshold) {
            builder.append(Math.round(nanos / threshold)).append(" $unit ")
            nanos %= threshold.toLong()
        }
    }

    fun lastAppend(threshold: Double, unit: String) {
        if (nanos > threshold || builder.isEmpty()) {
            builder.append(if (threshold > zero()) Math.round(nanos / threshold) else nanos).append(" $unit")
        }
        finished = true
    }

    fun append(step: TimeUnit, threshold: Double, unit: String) {
        if (!finished) {
            when (step) {
                limit -> lastAppend(threshold, unit)
                else -> intermediateAppend(threshold, unit)
            }
        }
    }

    append(TimeUnit.DAYS, NANO_DAY, "d")
    append(TimeUnit.HOURS, NANO_HOUR, "h")
    append(TimeUnit.MINUTES, NANO_MINUTE, "m")
    append(TimeUnit.SECONDS, NANO_SECOND, "s")
    append(TimeUnit.MILLISECONDS, NANO_MILLI, "ms")
    append(TimeUnit.MICROSECONDS, NANO_MICRO, "us")
    append(TimeUnit.NANOSECONDS, zero(), "ns")

    return builder.toString().trim()
}

public object ago

public object fromNow

public fun today() = LocalDate.now()

public fun now() = LocalDateTime.now()

public val Int.nanoseconds: Duration
    get() = Duration.ofNanos(toLong())

public val Int.microseconds: Duration
    get() = Duration.ofNanos(toLong() * 1000L)

public val Int.milliseconds: Duration
    get() = Duration.ofMillis(toLong())

public val Int.seconds: Duration
    get() = Duration.ofSeconds(toLong())

public val Int.minutes: Duration
    get() = Duration.ofMinutes(toLong())

public val Int.hours: Duration
    get() = Duration.ofHours(toLong())

public val Int.days: Period
    get() = Period.ofDays(this)

public val Int.weeks: Period
    get() = Period.ofWeeks(this)

public val Int.months: Period
    get() = Period.ofMonths(this)

public val Int.years: Period
    get() = Period.ofYears(this)

public val Duration.ago: LocalDateTime
    get() =  now() - this

public val Duration.fromNow: LocalDateTime
    get() =  now() + this

public val Period.ago: LocalDate
    get() = today() - this

public val Period.fromNow: LocalDate
    get() = today() + this

public infix fun Int.nanoseconds(fromNow: fromNow) = now().plusNanos(toLong())

public infix fun Int.nanoseconds(ago: ago) = now().minusNanos(toLong())

public infix fun Int.microseconds(fromNow: fromNow) = now().plusNanos(1000L * toLong())

public infix fun Int.microseconds(ago: ago) = now().minusNanos(1000L * toLong())

public infix fun Int.milliseconds(fromNow: fromNow) = now().plusNanos(1000000L * toLong())

public infix fun Int.milliseconds(ago: ago) = now().minusNanos(1000000L * toLong())

public infix fun Int.seconds(fromNow: fromNow) = now().plusSeconds(toLong())

public infix fun Int.seconds(ago: ago) = now().minusSeconds(toLong())

public infix fun Int.minutes(fromNow: fromNow) = now().plusMinutes(toLong())

public infix fun Int.minutes(ago: ago) = now().minusMinutes(toLong())

public infix fun Int.hours(fromNow: fromNow) = now().plusHours(toLong())

public infix fun Int.hours(ago: ago) = now().minusHours(toLong())

public infix fun Int.days(fromNow: fromNow) = today().plusDays(toLong())

public infix fun Int.days(ago: ago) = today().minusDays(toLong())

public infix fun Int.weeks(fromNow: fromNow) = today().plusWeeks(toLong())

public infix fun Int.weeks(ago: ago) = today().minusWeeks(toLong())

public infix fun Int.months(fromNow: fromNow) = today().plusMonths(toLong())

public infix fun Int.months(ago: ago) = today().minusMonths(toLong())

public infix fun Int.years(fromNow: fromNow) = today().plusYears(toLong())

public infix fun Int.years(ago: ago) = today().minusYears(toLong())
