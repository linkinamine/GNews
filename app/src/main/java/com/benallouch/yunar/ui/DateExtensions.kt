package com.benallouch.yunar.ui

import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun Date.parseDate(offset: Int): String {

    Log.d("offset", offset.toString())

    val differenceInMillis = abs(System.currentTimeMillis() - this.time)

    val differenceWithOffSet: Long = TimeUnit.SECONDS.convert(differenceInMillis + offset, TimeUnit.MILLISECONDS)
    return differenceWithOffSet.toInt().compoundDuration()
}

fun getCurrentOffset(): Int {
    val timezone = TimeZone.getDefault()
    return timezone.getOffset(Date().time) / 1000
}

private fun Int.compoundDuration(): String {
    if (this < 0) return ""
    if (this == 0) return "0h ago"
    val hours: Int
    val minutes: Int
    var divisor: Int = 60 * 60
    var rem = 0
    var result: String

    hours = this / divisor
    rem %= divisor
    divisor /= 60
    minutes = rem / divisor

    result = if (hours > 0) "${hours}h ago"
    else "${minutes}m ago"

    return result
}