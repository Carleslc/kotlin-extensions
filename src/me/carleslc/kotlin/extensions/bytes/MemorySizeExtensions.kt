package me.carleslc.kotlin.extensions.bytes

val Long.bits: BitValue get() = BitValue(this)
val Long.bit: BitValue get() = bits

val Int.bits: BitValue get() = toLong().bits
val Int.bit: BitValue get() = bits

val Long.bytes: BitValue get() = times(8).bits
val Long.byte: BitValue get() = bytes
val Long.B: BitValue get() = bytes

val Int.bytes: BitValue get() = toLong().bytes
val Int.byte: BitValue get() = bytes
val Int.B: BitValue get() = bytes

val Long.kibibytes: BitValue get() = times(1024).bytes
val Long.kibibyte: BitValue get() = kibibytes
val Long.KiB: BitValue get() = kibibytes

val Int.kibibytes: BitValue get() = toLong().kibibytes
val Int.kibibyte: BitValue get() = kibibytes
val Int.KiB: BitValue get() = kibibytes

val Long.kilobytes: BitValue get() = times(1000).bytes
val Long.kilobyte: BitValue get() = kilobytes
val Long.KB: BitValue get() = kilobytes

val Int.kilobytes: BitValue get() = toLong().kilobytes
val Int.kilobyte: BitValue get() = kilobytes
val Int.KB: BitValue get() = kilobytes

val Long.mebibytes: BitValue get() = times(1024).kibibytes
val Long.mebibyte: BitValue get() = mebibytes
val Long.MiB: BitValue get() = mebibytes

val Int.mebibytes: BitValue get() = toLong().mebibytes
val Int.mebibyte: BitValue get() = mebibytes
val Int.MiB: BitValue get() = mebibytes

val Long.megabytes: BitValue get() = times(1000).kilobytes
val Long.megabyte: BitValue get() = megabytes
val Long.MB: BitValue get() = megabytes

val Int.megabytes: BitValue get() = toLong().megabytes
val Int.megabyte: BitValue get() = megabytes
val Int.MB: BitValue get() = megabytes

val Long.gibibytes: BitValue get() = times(1024).mebibytes
val Long.gibibyte: BitValue get() = gibibytes
val Long.GiB: BitValue get() = gibibytes

val Int.gibibytes: BitValue get() = toLong().gibibytes
val Int.gibibyte: BitValue get() = gibibytes
val Int.GiB: BitValue get() = gibibytes

val Long.gigabytes: BitValue get() = times(1000).megabytes
val Long.gigabyte: BitValue get() = gigabytes
val Long.GB: BitValue get() = gigabytes

val Int.gigabytes: BitValue get() = toLong().gigabytes
val Int.gigabyte: BitValue get() = gigabytes
val Int.GB: BitValue get() = gigabytes

val Long.tebibytes: BitValue get() = times(1024).gibibytes
val Long.tebibyte: BitValue get() = tebibytes
val Long.TiB: BitValue get() = tebibytes

val Int.tebibytes: BitValue get() = toLong().tebibytes
val Int.tebibyte: BitValue get() = tebibytes
val Int.TiB: BitValue get() = tebibytes

val Long.terabytes: BitValue get() = times(1000).gigabytes
val Long.terabyte: BitValue get() = terabytes
val Long.TB: BitValue get() = terabytes

val Int.terabytes: BitValue get() = toLong().terabytes
val Int.terabyte: BitValue get() = terabytes
val Int.TB: BitValue get() = terabytes

val Long.pebibytes: BitValue get() = times(1024).tebibytes
val Long.pebibyte: BitValue get() = pebibytes
val Long.PiB: BitValue get() = pebibytes

val Int.pebibytes: BitValue get() = toLong().pebibytes
val Int.pebibyte: BitValue get() = pebibytes
val Int.PiB: BitValue get() = pebibytes

val Long.petabytes: BitValue get() = times(1000).terabytes
val Long.petabyte: BitValue get() = petabytes
val Long.PB: BitValue get() = petabytes

val Int.petabytes: BitValue get() = toLong().petabytes
val Int.petabyte: BitValue get() = petabytes
val Int.PB: BitValue get() = petabytes

data class BitValue internal constructor(val bits: Long) {
    val toBits = bits
    val toBytes = toBits / 8
    val B = toBytes
    val toKibibytes = toBytes / 1024
    val toKiloBytes = toBytes / 1000
    val KiB = toKibibytes
    val KB = toKiloBytes
    val toMebibytes = toKibibytes / 1024
    val toMegaBytes = toKiloBytes/ 1000
    val MiB = toMebibytes
    val MB = toMegaBytes
    val toGibibytes = toMebibytes / 1024
    val toGigabytes = toMegaBytes / 1000
    val GiB = toGibibytes
    val GB = toGigabytes
    val toTebibytes = toGibibytes / 1024
    val toTerabytes = toGigabytes / 1000
    val TiB = toTebibytes
    val TB = toTerabytes
    val toPebibytes = toTebibytes / 1024
    val toPetabytes = toTerabytes / 1000
    val PiB = toPebibytes
    val PB = toPetabytes
}
