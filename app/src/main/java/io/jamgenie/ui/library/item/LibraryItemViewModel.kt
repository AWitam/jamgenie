package io.jamgenie.ui.library.item

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.jamgenie.data.library.LibraryItem
import io.jamgenie.data.library.LibraryRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LibraryItemUIState(
    val item: LibraryItem?
)


class LibraryItemViewModel(
    savedStateHandle: SavedStateHandle,
    private val libraryRepository: LibraryRepository
) : ViewModel() {

    private val libraryItemId: String = checkNotNull(savedStateHandle["itemId"])

    private val _uiState =
        MutableStateFlow(LibraryItemUIState(null))

    val uiState: StateFlow<LibraryItemUIState> = _uiState.asStateFlow()

    init {
        val item = libraryRepository.getLibraryItem(libraryItemId)
        _uiState.value = LibraryItemUIState(item)
    }

    /**
     * Factory for LibraryItemViewModel that takes as a dependency
     */
    companion object {
        fun provideFactory(

            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,

            ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    @Suppress("UNCHECKED_CAST")
                    return LibraryItemViewModel(
                        libraryRepository = LibraryRepository(),
                        savedStateHandle = handle
                    ) as T
                }
            }
    }

}