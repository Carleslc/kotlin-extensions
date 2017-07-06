package me.carleslc.kotlin.extensions.time

import java.text.SimpleDateFormat
import java.time.*
import java.util.Date

public object ago
public object fromNow
public val since = fromNow
public val later = fromNow

private val agoRef = ago
private val fromNowRef = fromNow

public data class PeriodDuration internal constructor(val period: Period, val duration: Duration)

public val PeriodDuration.ago: LocalDateTime get() = Dates.of(period, duration, agoRef)
public val PeriodDuration.fromNow: LocalDateTime get() = Dates.of(period, duration, fromNowRef)
public val PeriodDuration.since: LocalDateTime get() = Dates.of(period, duration, fromNowRef)
public val PeriodDuration.later: LocalDateTime get() = Dates.of(period, duration, fromNowRef)

public object Dates {

    fun of(date: LocalDate, time: LocalTime): LocalDateTime = LocalDateTime.of(date, time)

    fun of(date: Period, time: Duration): PeriodDuration = PeriodDuration(date, time)
    fun of(date: LocalDate, time: Duration): PeriodDuration = of(date.period, time)
    fun of(date: Period, time: LocalTime): PeriodDuration = of(date, time.duration)

    fun of(date: Period, time: Duration, fromNow: fromNow): LocalDateTime = of(date.fromNow.toLocalDate(), time.fromNow.toLocalTime())
    fun of(date: Period, time: Duration, ago: ago): LocalDateTime = of(date.ago.toLocalDate(), time.ago.toLocalTime())

}

public fun now() = LocalDateTime.now()

public fun time() = LocalTime.now()
public fun nextMinute() = time() + 1.minute
public fun nextHour() = time() + 1.hour
public fun lastMinute() = time() - 1.minute
public fun lastHour() = time() - 1.hour

public fun date() = LocalDate.now()
public fun today() = date()
public fun tomorrow() = today() + 1.day
public fun nextWeek() = today() + 1.week
public fun nextMonth() = today() + 1.month
public fun nextYear() = today() + 1.year
public fun yesterday() = today() - 1.day
public fun lastWeek() = today() - 1.week
public fun lastMonth() = today() - 1.month
public fun lastYear() = today() - 1.year

public fun LocalDateTime.destructured(): Pair<LocalDate, LocalTime> = toLocalDate() to toLocalTime()

public fun LocalDateTime.toDate(zoneId: ZoneId = ZoneId.systemDefault()): Date = Date.from(atZone(zoneId).toInstant())
public fun Date.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime = LocalDateTime.ofInstant(toInstant(), zoneId)

public fun String.parseDate(formatter: SimpleDateFormat = SimpleDateFormat(), zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime = formatter.parse(this).toLocalDateTime(zoneId)
public fun LocalDateTime.format(formatter: SimpleDateFormat = SimpleDateFormat(), zoneId: ZoneId = ZoneId.systemDefault()): String = formatter.format(toDate(zoneId))

public fun LocalDateTime.isMonday() = dayOfWeek == DayOfWeek.MONDAY
public fun LocalDateTime.isTuesday() = dayOfWeek == DayOfWeek.TUESDAY
public fun LocalDateTime.isWednesday() = dayOfWeek == DayOfWeek.WEDNESDAY
public fun LocalDateTime.isThursday() = dayOfWeek == DayOfWeek.THURSDAY
public fun LocalDateTime.isFriday() = dayOfWeek == DayOfWeek.FRIDAY
public fun LocalDateTime.isSaturday() = dayOfWeek == DayOfWeek.SATURDAY
public fun LocalDateTime.isSunday() = dayOfWeek == DayOfWeek.SUNDAY

public fun LocalDate.atTime(time: LocalDateTime): LocalDateTime = atTime(time.toLocalTime())

public fun LocalDate.atTime(duration: Duration): LocalDateTime {
    val hours = duration.toHours()
    val hour = maxOf(hours, 24).toInt()
    var aux = duration.minusHours(hours)
    val minutes = aux.toMinutes()
    val minute = minutes.toInt()
    aux = aux.minusMinutes(minutes)
    val second = aux.seconds.toInt()
    return atTime(hour, minute, second, aux.nano)
}

public fun LocalTime.atDate(date: LocalDateTime): LocalDateTime = atDate(date.toLocalDate())

public val LocalDate.period: Period get() = Period.of(year, monthValue, dayOfMonth)
public val LocalTime.duration: Duration get() = toNanoOfDay().nanoseconds

public val LocalDate.fromNow: LocalDateTime get() = atTime(now())
public val LocalDate.since: LocalDateTime get() = fromNow
public val LocalDate.later: LocalDateTime get() = fromNow

public val LocalTime.fromNow: LocalDateTime get() = atDate(now())
public val LocalTime.since: LocalDateTime get() = fromNow
public val LocalTime.later: LocalDateTime get() = fromNow

public operator fun Period.plus(dateTime: LocalDateTime) = dateTime + this
public operator fun Duration.plus(dateTime: LocalDateTime) = dateTime + this

public fun Duration.toSeconds(): Long = seconds

public val Duration.ago: LocalDateTime get() = now() - this
public val Duration.fromNow: LocalDateTime get() = now() + this
public val Duration.since: LocalDateTime get() = fromNow
public val Duration.later: LocalDateTime get() = fromNow

public val Period.ago: LocalDateTime get() = now() - this
public val Period.fromNow: LocalDateTime get() = now() + this
public val Period.since: LocalDateTime get() = fromNow
public val Period.later: LocalDateTime get() = fromNow

public val Long.nanoseconds: Duration get() = run(Duration::ofNanos)
public val Long.nanosecond: Duration get() = nanoseconds

public val Long.microseconds: Duration get() = Duration.ofNanos(this * 1000L)
public val Long.microsecond: Duration get() = microseconds

public val Long.milliseconds: Duration get() = run(Duration::ofMillis)
public val Long.millisecond: Duration get() = milliseconds

public val Long.seconds: Duration get() = run(Duration::ofSeconds)
public val Long.second: Duration get() = seconds

public val Long.minutes: Duration get() = run(Duration::ofMinutes)
public val Long.minute: Duration get() = minutes

public val Long.hours: Duration get() = run(Duration::ofHours)
public val Long.hour: Duration get() = hours

public val Long.days: Period get() = toInt().days
public val Long.day: Period get() = days

public val Long.weeks: Period get() = toInt().weeks
public val Long.week: Period get() = weeks

public val Long.months: Period get() = toInt().months
public val Long.month: Period get() = months

public val Long.years: Period get() = toInt().years
public val Long.year: Period get() = years

public val Int.nanoseconds: Duration get() = toLong().nanoseconds
public val Int.nanosecond: Duration get() = nanoseconds

public val Int.microseconds: Duration get() = toLong().microseconds
public val Int.microsecond: Duration get() = microseconds

public val Int.milliseconds: Duration get() = toLong().milliseconds
public val Int.millisecond: Duration get() = milliseconds

public val Int.seconds: Duration get() = toLong().seconds
public val Int.second: Duration get() = seconds

public val Int.minutes: Duration get() = toLong().minutes
public val Int.minute: Duration get() = minutes

public val Int.hours: Duration get() = toLong().hours
public val Int.hour: Duration get() = hours

public val Int.days: Period get() = run(Period::ofDays)
public val Int.day: Period get() = days

public val Int.weeks: Period get() = run(Period::ofWeeks)
public val Int.week: Period get() = weeks

public val Int.months: Period get() = run(Period::ofMonths)
public val Int.month: Period get() = months

public val Int.years: Period get() = run(Period::ofYears)
public val Int.year: Period get() = years

public infix fun Long.nanoseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(this)
public infix fun Long.nanoseconds(ago: ago): LocalDateTime = now().minusNanos(this)

public infix fun Long.microseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(times(1000L))
public infix fun Long.microseconds(ago: ago): LocalDateTime = now().minusNanos(times(1000L))

public infix fun Long.milliseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(times(1_000_000L))
public infix fun Long.milliseconds(ago: ago): LocalDateTime = now().minusNanos(times(1_000_000L))

public infix fun Long.seconds(fromNow: fromNow): LocalDateTime = now().plusSeconds(this)
public infix fun Long.seconds(ago: ago): LocalDateTime = now().minusSeconds(this)

public infix fun Long.minutes(fromNow: fromNow): LocalDateTime = now().plusMinutes(this)
public infix fun Long.minutes(ago: ago): LocalDateTime = now().minusMinutes(this)

public infix fun Long.hours(fromNow: fromNow): LocalDateTime = now().plusHours(this)
public infix fun Long.hours(ago: ago): LocalDateTime = now().minusHours(this)

public infix fun Long.days(fromNow: fromNow): LocalDateTime = now().plusDays(this)
public infix fun Long.days(ago: ago): LocalDateTime = now().minusDays(this)

public infix fun Long.weeks(fromNow: fromNow): LocalDateTime = now().plusWeeks(this)
public infix fun Long.weeks(ago: ago): LocalDateTime = now().minusWeeks(this)

public infix fun Long.months(fromNow: fromNow): LocalDateTime = now().plusMonths(this)
public infix fun Long.months(ago: ago): LocalDateTime = now().minusMonths(this)

public infix fun Long.years(fromNow: fromNow): LocalDateTime = now().plusYears(this)
public infix fun Long.years(ago: ago): LocalDateTime = now().minusYears(this)

public infix fun Long.nanosecond(fromNow: fromNow): LocalDateTime = this nanoseconds fromNow
public infix fun Long.nanosecond(ago: ago): LocalDateTime = this nanoseconds ago

public infix fun Long.microsecond(fromNow: fromNow): LocalDateTime = this microseconds fromNow
public infix fun Long.microsecond(ago: ago): LocalDateTime = this microseconds ago

public infix fun Long.millisecond(fromNow: fromNow): LocalDateTime = this milliseconds fromNow
public infix fun Long.millisecond(ago: ago): LocalDateTime = this milliseconds ago

public infix fun Long.second(fromNow: fromNow): LocalDateTime = this seconds fromNow
public infix fun Long.second(ago: ago): LocalDateTime = this seconds ago

public infix fun Long.minute(fromNow: fromNow): LocalDateTime = this minutes fromNow
public infix fun Long.minute(ago: ago): LocalDateTime = this minutes ago

public infix fun Long.hour(fromNow: fromNow): LocalDateTime = this hours fromNow
public infix fun Long.hour(ago: ago): LocalDateTime = this hours ago

public infix fun Long.day(fromNow: fromNow): LocalDateTime = this days fromNow
public infix fun Long.day(ago: ago): LocalDateTime = this days ago

public infix fun Long.week(fromNow: fromNow): LocalDateTime = this weeks fromNow
public infix fun Long.week(ago: ago): LocalDateTime = this weeks ago

public infix fun Long.month(fromNow: fromNow): LocalDateTime = this months fromNow
public infix fun Long.month(ago: ago): LocalDateTime = this months ago

public infix fun Long.year(fromNow: fromNow): LocalDateTime = this years fromNow
public infix fun Long.year(ago: ago): LocalDateTime = this years ago

public infix fun Int.nanoseconds(fromNow: fromNow): LocalDateTime = toLong() nanoseconds fromNow
public infix fun Int.nanoseconds(ago: ago): LocalDateTime = toLong() nanoseconds ago

public infix fun Int.microseconds(fromNow: fromNow): LocalDateTime = toLong() microseconds fromNow
public infix fun Int.microseconds(ago: ago): LocalDateTime = toLong() microseconds ago

public infix fun Int.milliseconds(fromNow: fromNow): LocalDateTime = toLong() milliseconds fromNow
public infix fun Int.milliseconds(ago: ago): LocalDateTime = toLong() milliseconds ago

public infix fun Int.seconds(fromNow: fromNow): LocalDateTime = toLong() seconds fromNow
public infix fun Int.seconds(ago: ago): LocalDateTime = toLong() seconds ago

public infix fun Int.minutes(fromNow: fromNow): LocalDateTime = toLong() minutes fromNow
public infix fun Int.minutes(ago: ago): LocalDateTime = toLong() minutes ago

public infix fun Int.hours(fromNow: fromNow): LocalDateTime = toLong() hours fromNow
public infix fun Int.hours(ago: ago): LocalDateTime = toLong() hours ago

public infix fun Int.days(fromNow: fromNow): LocalDateTime = toLong() days fromNow
public infix fun Int.days(ago: ago): LocalDateTime = toLong() days ago

public infix fun Int.weeks(fromNow: fromNow): LocalDateTime = toLong() weeks fromNow
public infix fun Int.weeks(ago: ago): LocalDateTime = toLong() weeks ago

public infix fun Int.months(fromNow: fromNow): LocalDateTime = toLong() months fromNow
public infix fun Int.months(ago: ago): LocalDateTime = toLong() months ago

public infix fun Int.years(fromNow: fromNow): LocalDateTime = toLong() years fromNow
public infix fun Int.years(ago: ago): LocalDateTime = toLong() years ago

public infix fun Int.nanosecond(fromNow: fromNow): LocalDateTime = toLong() nanoseconds fromNow
public infix fun Int.nanosecond(ago: ago): LocalDateTime = toLong() nanoseconds ago

public infix fun Int.microsecond(fromNow: fromNow): LocalDateTime = toLong() microseconds fromNow
public infix fun Int.microsecond(ago: ago): LocalDateTime = toLong() microseconds ago

public infix fun Int.millisecond(fromNow: fromNow): LocalDateTime = toLong() milliseconds fromNow
public infix fun Int.millisecond(ago: ago): LocalDateTime = toLong() milliseconds ago

public infix fun Int.second(fromNow: fromNow): LocalDateTime = toLong() seconds fromNow
public infix fun Int.second(ago: ago): LocalDateTime = toLong() seconds ago

public infix fun Int.minute(fromNow: fromNow): LocalDateTime = toLong() minutes fromNow
public infix fun Int.minute(ago: ago): LocalDateTime = toLong() minutes ago

public infix fun Int.hour(fromNow: fromNow): LocalDateTime = toLong() hours fromNow
public infix fun Int.hour(ago: ago): LocalDateTime = toLong() hours ago

public infix fun Int.day(fromNow: fromNow): LocalDateTime = toLong() days fromNow
public infix fun Int.day(ago: ago): LocalDateTime = toLong() days ago

public infix fun Int.week(fromNow: fromNow): LocalDateTime = toLong() weeks fromNow
public infix fun Int.week(ago: ago): LocalDateTime = toLong() weeks ago

public infix fun Int.month(fromNow: fromNow): LocalDateTime = toLong() months fromNow
public infix fun Int.month(ago: ago): LocalDateTime = toLong() months ago

public infix fun Int.year(fromNow: fromNow): LocalDateTime = toLong() years fromNow
public infix fun Int.year(ago: ago): LocalDateTime = toLong() years ago
