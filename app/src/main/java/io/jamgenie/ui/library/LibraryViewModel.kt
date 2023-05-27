package io.jamgenie.ui.library

import androidx.lifecycle.ViewModel
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUIState(emptyList(), LibraryTab.ALL))
    val uiState: StateFlow<LibraryUIState> = _uiState.asStateFlow()

    init {
        val listItems = LibraryRepository().getAllLibraryItems()
        _uiState.value = LibraryUIState(listItems, LibraryTab.ALL)
    }

    fun onTabSelected(tab: LibraryTab) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }


}

