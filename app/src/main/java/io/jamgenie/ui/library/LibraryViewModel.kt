package io.jamgenie.ui.library

import androidx.lifecycle.ViewModel
import io.jamgenie.data.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUIState(emptyList(), LibraryTab.ALL, null))
    val uiState: StateFlow<LibraryUIState> = _uiState.asStateFlow()

    init {
        val listItems = LibraryRepository().getAllLibraryItems()
        _uiState.value = LibraryUIState(listItems, LibraryTab.ALL, null)
    }

    fun onTabSelected(tab: LibraryTab) {
        if (tab === LibraryTab.ALL) {
            _uiState.value = _uiState.value.copy(
                selectedTab = tab,
                listItems = LibraryRepository().getAllLibraryItems()
            )
        }
        if (tab === LibraryTab.ROUTINES) {
            _uiState.value = _uiState.value.copy(
                selectedTab = tab,
                listItems = LibraryRepository().getRoutines()
            )
        }
        if (tab === LibraryTab.PRACTICE_ITEMS) {
            _uiState.value = _uiState.value.copy(
                selectedTab = tab,
                listItems = LibraryRepository().getPracticeItems()
            )
        }
    }


}

