package io.jamgenie.ui.home

import androidx.lifecycle.ViewModel
import io.jamgenie.data.home.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        val user = UserRepository().getUser()
        _uiState.value = HomeUIState(user)
    }


}
