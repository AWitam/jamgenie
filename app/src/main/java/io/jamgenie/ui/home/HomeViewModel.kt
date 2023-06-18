package io.jamgenie.ui.home

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.jamgenie.data.home.UserRepository
import io.jamgenie.data.library.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        val user = userRepository.getUser()
        val currentPracticeRoutineTitle = user.currentPracticeRoutineId?.let {
            LibraryRepository().getLibraryItem(
                it
            ).title
        } ?: "No practice routine selected"
        _uiState.value = HomeUIState(user, currentPracticeRoutineTitle)
    }

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
                    return HomeViewModel(
                        userRepository = UserRepository()
                    ) as T
                }
            }
    }


}
