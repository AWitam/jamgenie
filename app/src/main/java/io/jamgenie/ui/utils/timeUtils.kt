package io.jamgenie.ui.utils


fun getFormattedTime(timeInMilliseconds: Long?): String {
    val totalSeconds = timeInMilliseconds?.div(1000) ?: 0
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}

fun secToMs(seconds: Int?): Long {
    return seconds?.times(1000)?.toLong() ?: 0
}

fun formatMinSec(timeInSeconds: Int): String {
    val minutes = timeInSeconds / 60
    val seconds = timeInSeconds % 60
    return "${minutes}min ${seconds}s"
}