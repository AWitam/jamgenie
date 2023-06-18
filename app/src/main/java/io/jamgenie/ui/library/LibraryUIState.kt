package io.jamgenie.ui.library

import io.jamgenie.data.library.LibraryItem

enum class LibraryTab {
    ROUTINES,
    PRACTICE_ITEMS
}

data class LibraryUIState(
    val routineListItems: List<LibraryItem.Routine>,
    val practiceItemListItems: List<LibraryItem.PracticeItem>,
    val selectedTab: LibraryTab,
    val searchQuery: String?
)