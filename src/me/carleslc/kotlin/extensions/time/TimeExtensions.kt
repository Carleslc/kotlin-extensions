@file:Suppress("NOTHING_TO_INLINE", "UNUSED_PARAMETER")

package me.carleslc.kotlin.extensions.time

import java.text.SimpleDateFormat
import java.time.*
import java.util.Date

object ago
object fromNow

val since = fromNow
val later = fromNow

private val agoRef = ago
private val fromNowRef = fromNow

data class PeriodDuration internal constructor(val period: Period, val duration: Duration)

val PeriodDuration.ago: LocalDateTime get() = Dates.of(period, duration, agoRef)
val PeriodDuration.fromNow: LocalDateTime get() = Dates.of(period, duration, fromNowRef)
val PeriodDuration.since: LocalDateTime get() = Dates.of(period, duration, fromNowRef)
val PeriodDuration.later: LocalDateTime get() = Dates.of(period, duration, fromNowRef)

object Dates {

    fun of(date: LocalDate, time: LocalTime): LocalDateTime = LocalDateTime.of(date, time)

    fun of(date: Period, time: Duration): PeriodDuration = PeriodDuration(date, time)
    fun of(date: LocalDate, time: Duration): PeriodDuration = of(date.period, time)
    fun of(date: Period, time: LocalTime): PeriodDuration = of(date, time.duration)

    fun of(date: Period, time: Duration, fromNow: fromNow): LocalDateTime = of(date.fromNow.toLocalDate(), time.fromNow.toLocalTime())
    fun of(date: Period, time: Duration, ago: ago): LocalDateTime = of(date.ago.toLocalDate(), time.ago.toLocalTime())

}

inline fun now() = LocalDateTime.now()

inline fun time() = LocalTime.now()
inline fun nextMinute() = time() + 1.minute
inline fun nextHour() = time() + 1.hour
inline fun lastMinute() = time() - 1.minute
inline fun lastHour() = time() - 1.hour

inline fun date() = LocalDate.now()
inline fun today() = date()
inline fun tomorrow() = today() + 1.day
inline fun nextWeek() = today() + 1.week
inline fun nextMonth() = today() + 1.month
inline fun nextYear() = today() + 1.year
inline fun yesterday() = today() - 1.day
inline fun lastWeek() = today() - 1.week
inline fun lastMonth() = today() - 1.month
inline fun lastYear() = today() - 1.year

inline fun LocalDateTime.destructured(): Pair<LocalDate, LocalTime> = toLocalDate() to toLocalTime()

inline fun LocalDateTime.toDate(zoneId: ZoneId = ZoneId.systemDefault()): Date = Date.from(atZone(zoneId).toInstant())
inline fun Date.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime = LocalDateTime.ofInstant(toInstant(), zoneId)

inline fun String.parseDate(formatter: SimpleDateFormat = SimpleDateFormat(), zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime = formatter.parse(this).toLocalDateTime(zoneId)
inline fun LocalDateTime.format(formatter: SimpleDateFormat = SimpleDateFormat(), zoneId: ZoneId = ZoneId.systemDefault()): String = formatter.format(toDate(zoneId))

inline fun LocalDateTime.isMonday() = dayOfWeek == DayOfWeek.MONDAY
inline fun LocalDateTime.isTuesday() = dayOfWeek == DayOfWeek.TUESDAY
inline fun LocalDateTime.isWednesday() = dayOfWeek == DayOfWeek.WEDNESDAY
inline fun LocalDateTime.isThursday() = dayOfWeek == DayOfWeek.THURSDAY
inline fun LocalDateTime.isFriday() = dayOfWeek == DayOfWeek.FRIDAY
inline fun LocalDateTime.isSaturday() = dayOfWeek == DayOfWeek.SATURDAY
inline fun LocalDateTime.isSunday() = dayOfWeek == DayOfWeek.SUNDAY

inline fun LocalDate.atTime(time: LocalDateTime): LocalDateTime = atTime(time.toLocalTime())

inline fun LocalDate.atTime(duration: Duration): LocalDateTime {
    val hours = duration.toHours()
    val hour = maxOf(hours, 24).toInt()
    var aux = duration.minusHours(hours)
    val minutes = aux.toMinutes()
    val minute = minutes.toInt()
    aux = aux.minusMinutes(minutes)
    val second = aux.seconds.toInt()
    return atTime(hour, minute, second, aux.nano)
}

inline fun LocalTime.atDate(date: LocalDateTime): LocalDateTime = atDate(date.toLocalDate())

inline val LocalDate.period: Period get() = Period.of(year, monthValue, dayOfMonth)
inline val LocalTime.duration: Duration get() = toNanoOfDay().nanoseconds

inline val LocalDate.fromNow: LocalDateTime get() = atTime(now())
inline val LocalDate.since: LocalDateTime get() = fromNow
inline val LocalDate.later: LocalDateTime get() = fromNow

inline val LocalTime.fromNow: LocalDateTime get() = atDate(now())
inline val LocalTime.since: LocalDateTime get() = fromNow
inline val LocalTime.later: LocalDateTime get() = fromNow

inline operator fun Period.plus(dateTime: LocalDateTime) = dateTime + this
inline operator fun Duration.plus(dateTime: LocalDateTime) = dateTime + this

inline fun Duration.toSeconds(): Long = seconds

inline val Duration.ago: LocalDateTime get() = now() - this
inline val Duration.fromNow: LocalDateTime get() = now() + this
inline val Duration.since: LocalDateTime get() = fromNow
inline val Duration.later: LocalDateTime get() = fromNow

inline val Period.ago: LocalDateTime get() = now() - this
inline val Period.fromNow: LocalDateTime get() = now() + this
inline val Period.since: LocalDateTime get() = fromNow
inline val Period.later: LocalDateTime get() = fromNow

inline val Long.nanoseconds: Duration get() = run(Duration::ofNanos)
inline val Long.nanosecond: Duration get() = nanoseconds

inline val Long.microseconds: Duration get() = Duration.ofNanos(this * 1000L)
inline val Long.microsecond: Duration get() = microseconds

inline val Long.milliseconds: Duration get() = run(Duration::ofMillis)
inline val Long.millisecond: Duration get() = milliseconds

inline val Long.seconds: Duration get() = run(Duration::ofSeconds)
inline val Long.second: Duration get() = seconds

inline val Long.minutes: Duration get() = run(Duration::ofMinutes)
inline val Long.minute: Duration get() = minutes

inline val Long.hours: Duration get() = run(Duration::ofHours)
inline val Long.hour: Duration get() = hours

inline val Long.daysDuration: Duration get() = toInt().daysDuration
inline val Long.dayDuration: Duration get() = daysDuration

inline val Long.weeksDuration: Duration get() = toInt().weeksDuration
inline val Long.weekDuration: Duration get() = weeksDuration

inline val Long.days: Period get() = toInt().days
inline val Long.day: Period get() = days

inline val Long.weeks: Period get() = toInt().weeks
inline val Long.week: Period get() = weeks

inline val Long.months: Period get() = toInt().months
inline val Long.month: Period get() = months

inline val Long.years: Period get() = toInt().years
inline val Long.year: Period get() = years

inline val Int.nanoseconds: Duration get() = toLong().nanoseconds
inline val Int.nanosecond: Duration get() = nanoseconds

inline val Int.microseconds: Duration get() = toLong().microseconds
inline val Int.microsecond: Duration get() = microseconds

inline val Int.milliseconds: Duration get() = toLong().milliseconds
inline val Int.millisecond: Duration get() = milliseconds

inline val Int.seconds: Duration get() = toLong().seconds
inline val Int.second: Duration get() = seconds

inline val Int.minutes: Duration get() = toLong().minutes
inline val Int.minute: Duration get() = minutes

inline val Int.hours: Duration get() = toLong().hours
inline val Int.hour: Duration get() = hours

inline val Int.daysDuration: Duration get() = (24 * this).hours
inline val Int.dayDuration: Duration get() = daysDuration

inline val Int.weeksDuration: Duration get() = (24 * 7 * this).hours
inline val Int.weekDuration: Duration get() = weeksDuration

inline val Int.days: Period get() = run(Period::ofDays)
inline val Int.day: Period get() = days

inline val Int.weeks: Period get() = run(Period::ofWeeks)
inline val Int.week: Period get() = weeks

inline val Int.months: Period get() = run(Period::ofMonths)
inline val Int.month: Period get() = months

inline val Int.years: Period get() = run(Period::ofYears)
inline val Int.year: Period get() = years

inline infix fun Long.nanoseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(this)
inline infix fun Long.nanoseconds(ago: ago): LocalDateTime = now().minusNanos(this)

inline infix fun Long.microseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(times(1000L))
inline infix fun Long.microseconds(ago: ago): LocalDateTime = now().minusNanos(times(1000L))

inline infix fun Long.milliseconds(fromNow: fromNow): LocalDateTime = now().plusNanos(times(1_000_000L))
inline infix fun Long.milliseconds(ago: ago): LocalDateTime = now().minusNanos(times(1_000_000L))

inline infix fun Long.seconds(fromNow: fromNow): LocalDateTime = now().plusSeconds(this)
inline infix fun Long.seconds(ago: ago): LocalDateTime = now().minusSeconds(this)

inline infix fun Long.minutes(fromNow: fromNow): LocalDateTime = now().plusMinutes(this)
inline infix fun Long.minutes(ago: ago): LocalDateTime = now().minusMinutes(this)

inline infix fun Long.hours(fromNow: fromNow): LocalDateTime = now().plusHours(this)
inline infix fun Long.hours(ago: ago): LocalDateTime = now().minusHours(this)

inline infix fun Long.days(fromNow: fromNow): LocalDateTime = now().plusDays(this)
inline infix fun Long.days(ago: ago): LocalDateTime = now().minusDays(this)

inline infix fun Long.weeks(fromNow: fromNow): LocalDateTime = now().plusWeeks(this)
inline infix fun Long.weeks(ago: ago): LocalDateTime = now().minusWeeks(this)

inline infix fun Long.months(fromNow: fromNow): LocalDateTime = now().plusMonths(this)
inline infix fun Long.months(ago: ago): LocalDateTime = now().minusMonths(this)

inline infix fun Long.years(fromNow: fromNow): LocalDateTime = now().plusYears(this)
inline infix fun Long.years(ago: ago): LocalDateTime = now().minusYears(this)

inline infix fun Long.nanosecond(fromNow: fromNow): LocalDateTime = this nanoseconds fromNow
inline infix fun Long.nanosecond(ago: ago): LocalDateTime = this nanoseconds ago

inline infix fun Long.microsecond(fromNow: fromNow): LocalDateTime = this microseconds fromNow
inline infix fun Long.microsecond(ago: ago): LocalDateTime = this microseconds ago

inline infix fun Long.millisecond(fromNow: fromNow): LocalDateTime = this milliseconds fromNow
inline infix fun Long.millisecond(ago: ago): LocalDateTime = this milliseconds ago

inline infix fun Long.second(fromNow: fromNow): LocalDateTime = this seconds fromNow
inline infix fun Long.second(ago: ago): LocalDateTime = this seconds ago

inline infix fun Long.minute(fromNow: fromNow): LocalDateTime = this minutes fromNow
inline infix fun Long.minute(ago: ago): LocalDateTime = this minutes ago

inline infix fun Long.hour(fromNow: fromNow): LocalDateTime = this hours fromNow
inline infix fun Long.hour(ago: ago): LocalDateTime = this hours ago

inline infix fun Long.day(fromNow: fromNow): LocalDateTime = this days fromNow
inline infix fun Long.day(ago: ago): LocalDateTime = this days ago

inline infix fun Long.week(fromNow: fromNow): LocalDateTime = this weeks fromNow
inline infix fun Long.week(ago: ago): LocalDateTime = this weeks ago

inline infix fun Long.month(fromNow: fromNow): LocalDateTime = this months fromNow
inline infix fun Long.month(ago: ago): LocalDateTime = this months ago

inline infix fun Long.year(fromNow: fromNow): LocalDateTime = this years fromNow
inline infix fun Long.year(ago: ago): LocalDateTime = this years ago

inline infix fun Int.nanoseconds(fromNow: fromNow): LocalDateTime = toLong() nanoseconds fromNow
inline infix fun Int.nanoseconds(ago: ago): LocalDateTime = toLong() nanoseconds ago

inline infix fun Int.microseconds(fromNow: fromNow): LocalDateTime = toLong() microseconds fromNow
inline infix fun Int.microseconds(ago: ago): LocalDateTime = toLong() microseconds ago

inline infix fun Int.milliseconds(fromNow: fromNow): LocalDateTime = toLong() milliseconds fromNow
inline infix fun Int.milliseconds(ago: ago): LocalDateTime = toLong() milliseconds ago

inline infix fun Int.seconds(fromNow: fromNow): LocalDateTime = toLong() seconds fromNow
inline infix fun Int.seconds(ago: ago): LocalDateTime = toLong() seconds ago

inline infix fun Int.minutes(fromNow: fromNow): LocalDateTime = toLong() minutes fromNow
inline infix fun Int.minutes(ago: ago): LocalDateTime = toLong() minutes ago

inline infix fun Int.hours(fromNow: fromNow): LocalDateTime = toLong() hours fromNow
inline infix fun Int.hours(ago: ago): LocalDateTime = toLong() hours ago

inline infix fun Int.days(fromNow: fromNow): LocalDateTime = toLong() days fromNow
inline infix fun Int.days(ago: ago): LocalDateTime = toLong() days ago

inline infix fun Int.weeks(fromNow: fromNow): LocalDateTime = toLong() weeks fromNow
inline infix fun Int.weeks(ago: ago): LocalDateTime = toLong() weeks ago

inline infix fun Int.months(fromNow: fromNow): LocalDateTime = toLong() months fromNow
inline infix fun Int.months(ago: ago): LocalDateTime = toLong() months ago

inline infix fun Int.years(fromNow: fromNow): LocalDateTime = toLong() years fromNow
inline infix fun Int.years(ago: ago): LocalDateTime = toLong() years ago

inline infix fun Int.nanosecond(fromNow: fromNow): LocalDateTime = toLong() nanoseconds fromNow
inline infix fun Int.nanosecond(ago: ago): LocalDateTime = toLong() nanoseconds ago

inline infix fun Int.microsecond(fromNow: fromNow): LocalDateTime = toLong() microseconds fromNow
inline infix fun Int.microsecond(ago: ago): LocalDateTime = toLong() microseconds ago

inline infix fun Int.millisecond(fromNow: fromNow): LocalDateTime = toLong() milliseconds fromNow
inline infix fun Int.millisecond(ago: ago): LocalDateTime = toLong() milliseconds ago

inline infix fun Int.second(fromNow: fromNow): LocalDateTime = toLong() seconds fromNow
inline infix fun Int.second(ago: ago): LocalDateTime = toLong() seconds ago

inline infix fun Int.minute(fromNow: fromNow): LocalDateTime = toLong() minutes fromNow
inline infix fun Int.minute(ago: ago): LocalDateTime = toLong() minutes ago

inline infix fun Int.hour(fromNow: fromNow): LocalDateTime = toLong() hours fromNow
inline infix fun Int.hour(ago: ago): LocalDateTime = toLong() hours ago

inline infix fun Int.day(fromNow: fromNow): LocalDateTime = toLong() days fromNow
inline infix fun Int.day(ago: ago): LocalDateTime = toLong() days ago

inline infix fun Int.week(fromNow: fromNow): LocalDateTime = toLong() weeks fromNow
inline infix fun Int.week(ago: ago): LocalDateTime = toLong() weeks ago

inline infix fun Int.month(fromNow: fromNow): LocalDateTime = toLong() months fromNow
inline infix fun Int.month(ago: ago): LocalDateTime = toLong() months ago

inline infix fun Int.year(fromNow: fromNow): LocalDateTime = toLong() years fromNow
inline infix fun Int.year(ago: ago): LocalDateTime = toLong() years ago
