package me.carleslc.extensions.time

private val SECOND = 1000f
private val MINUTE = 60 * SECOND
private val HOUR = 60 * MINUTE
private val DAY = 24 * HOUR

public fun getTimeFrom(startMillis: Long): String {
	var millis = System.currentTimeMillis() - startMillis
	val builder = StringBuilder()
	if (millis > DAY) {
		builder.append(Math.round(millis / DAY)).append(" d ")
		millis %= DAY.toLong()
	}
	if (millis > HOUR) {
		builder.append(Math.round(millis / HOUR)).append(" h ")
		millis %= HOUR.toLong()
	}
	if (millis > MINUTE) {
		builder.append(Math.round(millis / MINUTE)).append(" m ")
		millis %= MINUTE.toLong()
	}
	if (millis > SECOND) {
		builder.append(Math.round(millis / SECOND)).append(" s ")
		millis %= SECOND.toLong()
	}
	if (millis > 0 || builder.length == 0) builder.append(millis).append(" ms")
	return builder.toString().trim()
}
