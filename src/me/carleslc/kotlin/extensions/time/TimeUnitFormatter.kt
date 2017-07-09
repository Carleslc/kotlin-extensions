package me.carleslc.kotlin.extensions.time

import java.util.concurrent.TimeUnit

open class TimeUnitFormatter protected constructor(val day: String,
                                                   val days: String = day,
                                                   val hour: String,
                                                   val hours: String = day,
                                                   val minute: String,
                                                   val minutes: String = minute,
                                                   val second: String,
                                                   val seconds: String = second,
                                                   val millisecond: String,
                                                   val milliseconds: String = millisecond,
                                                   val microsecond: String,
                                                   val microseconds: String = microsecond,
                                                   val nanosecond: String,
                                                   val nanoseconds: String = nanosecond) {

    companion object Defaults {
        val SHORT by lazy { TimeUnitFormatter(day = "d", hour = "h", minute = "m", second = "s", millisecond = "ms", microsecond = "us", nanosecond = "ns") }
        val LONG by lazy { TimeUnitFormatter("day", "days", "hour", "hours", "minute", "minutes", "second", "seconds", "millisecond", "milliseconds", "microsecond", "microseconds", "nanosecond", "nanoseconds") }
    }

    open fun format(value: Long, unit: TimeUnit) = "$value ${get(value, unit)} "

    open fun formatLast(value: Number, unit: TimeUnit) = format(value.toLong(), unit)

    protected fun get(value: Long, unit: TimeUnit): String {
        val isPlural = Math.abs(value) != 1L

        fun get(singular: String, plural: String) = if (isPlural) plural else singular

        return when (unit) {
            TimeUnit.DAYS -> get(day, days)
            TimeUnit.HOURS -> get(hour, hours)
            TimeUnit.MINUTES -> get(minute, minutes)
            TimeUnit.SECONDS -> get(second, seconds)
            TimeUnit.MILLISECONDS -> get(millisecond, milliseconds)
            TimeUnit.MICROSECONDS -> get(microsecond, microseconds)
            TimeUnit.NANOSECONDS -> get(nanosecond, nanoseconds)
        }
    }

}
