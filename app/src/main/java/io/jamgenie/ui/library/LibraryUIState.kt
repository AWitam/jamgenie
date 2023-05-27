package io.jamgenie.ui.library

import io.jamgenie.data.LibraryItem

enum class LibraryTab {
    ALL,
    ROUTINES,
    PRACTICE_ITEMS
}

data class LibraryUIState(
    val listItems: List<LibraryItem>,
    val selectedTab: LibraryTab
)