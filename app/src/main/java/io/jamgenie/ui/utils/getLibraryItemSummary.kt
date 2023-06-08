package io.jamgenie.ui.utils

import io.jamgenie.data.LibraryItem

data class LibraryItemSummary(val durationInMinutes: String?, val totalPracticeItems: Int?)

fun getLibraryItemSummary(item: LibraryItem): LibraryItemSummary {

    val duration = when (item) {
        is LibraryItem.Routine -> item.practiceItems.sumOf { it.durationInMinutes }
            .toString() + " min"

        is LibraryItem.PracticeItem -> item.durationInMinutes.toString() + " min"
    }

    val totalPracticeItems = when (item) {
        is LibraryItem.Routine -> item.practiceItems.size
        else -> null
    }

    return LibraryItemSummary(duration, totalPracticeItems)
}