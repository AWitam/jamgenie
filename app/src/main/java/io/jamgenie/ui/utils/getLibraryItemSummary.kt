package io.jamgenie.ui.utils

import io.jamgenie.data.LibraryItem

data class LibraryItemSummary(val formattedDuration: String?, val totalPracticeItems: Int?)

fun getLibraryItemSummary(item: LibraryItem): LibraryItemSummary {

    val duration: String = when (item) {
        is LibraryItem.Routine -> {
            val totalDurationInSeconds = item.practiceItems.sumOf { it.durationInSeconds }
            formatMinSec(totalDurationInSeconds)
        }

        is LibraryItem.PracticeItem -> formatMinSec(item.durationInSeconds)
    }


    val totalPracticeItems = when (item) {
        is LibraryItem.Routine -> item.practiceItems.size
        else -> null
    }

    return LibraryItemSummary(formattedDuration = duration, totalPracticeItems)
}


