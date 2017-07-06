package me.carleslc.kotlin.extensions.time

import me.carleslc.kotlin.extensions.number.zero
import me.carleslc.kotlin.extensions.standard.with
import java.io.PrintStream
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

private val NANO_MICRO by lazy { 1000.toDouble() }
private val NANO_MILLI by lazy { 1000 * NANO_MICRO }
private val NANO_SECOND by lazy { 1000 * NANO_MILLI }
private val NANO_MINUTE by lazy { 60 * NANO_SECOND }
private val NANO_HOUR by lazy { 60 * NANO_MINUTE }
private val NANO_DAY by lazy { 24 * NANO_HOUR }

public object Durations {

    fun from(time: Long, unit: TimeUnit): Duration = Duration.ofNanos(nanos() - unit.toNanos(time))

    fun fromMillis(millis: Long): Duration = from(millis, TimeUnit.MILLISECONDS)

    fun fromNanos(nanos: Long): Duration = from(nanos, TimeUnit.NANOSECONDS)

}

public fun measure(block: () -> Unit): Duration = measureNanoTime(block).nanoseconds

public fun measureAndPrint(limit: TimeUnit = TimeUnit.NANOSECONDS,
                           transformation: ((String) -> String)? = null,
                           outputStream: PrintStream = System.out,
                           block: () -> Unit) = measure(block).humanize(limit, transformation).run(outputStream::println)

public fun Duration.humanize(limit: TimeUnit = TimeUnit.NANOSECONDS, transformation: ((String) -> String)?): String {
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

    return builder.toString().trim().with(transformation)
}

public object ago

public object fromNow

public fun today() = LocalDate.now()

public fun now() = LocalDateTime.now()

public fun millis() = System.currentTimeMillis()

public fun nanos() = System.nanoTime()

public val Long.nanoseconds: Duration
    get() = run(Duration::ofNanos)

public val Long.microseconds: Duration
    get() = Duration.ofNanos(this * 1000L)

public val Long.milliseconds: Duration
    get() = run(Duration::ofMillis)

public val Long.seconds: Duration
    get() = run(Duration::ofSeconds)

public val Long.minutes: Duration
    get() = run(Duration::ofMinutes)

public val Long.hours: Duration
    get() = run(Duration::ofHours)

public val Long.hour: Duration
    get() = hours

public val Int.days: Period
    get() = run(Period::ofDays)

public val Int.day: Period
    get() = days

public val Int.weeks: Period
    get() = run(Period::ofWeeks)

public val Int.week: Period
    get() = weeks

public val Int.months: Period
    get() = run(Period::ofMonths)

public val Int.month: Period
    get() = months

public val Int.years: Period
    get() = run(Period::ofYears)

public val Int.year: Period
    get() = years

public val Duration.ago: LocalDateTime
    get() =  now() - this

public val Duration.fromNow: LocalDateTime
    get() =  now() + this

public val Period.ago: LocalDate
    get() = today() - this

public val Period.fromNow: LocalDate
    get() = today() + this

public infix fun Long.nanoseconds(fromNow: fromNow) = now().plusNanos(toLong())

public infix fun Long.nanoseconds(ago: ago) = now().minusNanos(toLong())

public infix fun Long.microseconds(fromNow: fromNow) = now().plusNanos(1000L * toLong())

public infix fun Long.microseconds(ago: ago) = now().minusNanos(1000L * toLong())

public infix fun Long.milliseconds(fromNow: fromNow) = now().plusNanos(1000000L * toLong())

public infix fun Long.milliseconds(ago: ago) = now().minusNanos(1000000L * toLong())

public infix fun Long.seconds(fromNow: fromNow) = now().plusSeconds(toLong())

public infix fun Long.seconds(ago: ago) = now().minusSeconds(toLong())

public infix fun Long.minutes(fromNow: fromNow) = now().plusMinutes(toLong())

public infix fun Long.minutes(ago: ago) = now().minusMinutes(toLong())

public infix fun Long.hours(fromNow: fromNow) = now().plusHours(toLong())

public infix fun Long.hours(ago: ago) = now().minusHours(toLong())

public infix fun Long.days(fromNow: fromNow) = today().plusDays(toLong())

public infix fun Long.days(ago: ago) = today().minusDays(toLong())

public infix fun Long.weeks(fromNow: fromNow) = today().plusWeeks(toLong())

public infix fun Long.weeks(ago: ago) = today().minusWeeks(toLong())

public infix fun Long.months(fromNow: fromNow) = today().plusMonths(toLong())

public infix fun Long.months(ago: ago) = today().minusMonths(toLong())

public infix fun Long.years(fromNow: fromNow) = today().plusYears(toLong())

public infix fun Long.years(ago: ago) = today().minusYears(toLong())
