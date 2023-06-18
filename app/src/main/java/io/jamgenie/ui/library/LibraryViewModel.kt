package io.jamgenie.ui.library

import androidx.lifecycle.ViewModel
import io.jamgenie.data.library.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LibraryViewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(LibraryUIState(emptyList(), emptyList(), LibraryTab.ROUTINES, null))
    val uiState: StateFlow<LibraryUIState> = _uiState.asStateFlow()

    init {
        val routinesList = LibraryRepository().getAllRoutines()
        val practiceItemsList = LibraryRepository().getAllPracticeItems()
        _uiState.value = LibraryUIState(routinesList, practiceItemsList, LibraryTab.ROUTINES, null)
    }

    fun onTabSelected(tab: LibraryTab) {
        if (tab === LibraryTab.ROUTINES) {
            _uiState.value = _uiState.value.copy(
                selectedTab = tab, routineListItems = LibraryRepository().getAllRoutines()
            )
        }
        if (tab === LibraryTab.PRACTICE_ITEMS) {
            _uiState.value = _uiState.value.copy(
                selectedTab = tab, practiceItemListItems = LibraryRepository().getAllPracticeItems()
            )
        }
    }


}

