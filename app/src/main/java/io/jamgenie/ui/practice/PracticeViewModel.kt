package io.jamgenie.ui.practice

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.LibraryRepository
import io.jamgenie.ui.utils.CountdownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class PracticeUIState(
    val currentItem: LibraryItem.PracticeItem? = null,
    val isCountdownRunning: Boolean = false,
    val countdownText: String = "00:00"
)

fun getFormattedTime(timeInMilliseconds: Long?): String {
    val totalSeconds = timeInMilliseconds?.div(1000) ?: 0
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}

fun getDurationInMs(timeInMinutes: Int?): Long {
    return timeInMinutes?.times(60)?.times(1000)?.toLong() ?: 0
}


class PracticeViewModel(
    savedStateHandle: SavedStateHandle, private val libraryRepository: LibraryRepository
) : ViewModel() {
    private var timer: CountdownTimer
    private val libraryItemId: String = checkNotNull(savedStateHandle["itemId"])

    private val _uiState = MutableStateFlow(PracticeUIState(null))

    val uiState: StateFlow<PracticeUIState> = _uiState.asStateFlow()

    init {
        val currentItem = when (val item = libraryRepository.getLibraryItem(libraryItemId)) {
            is LibraryItem.Routine -> item.practiceItems[0]
            is LibraryItem.PracticeItem -> item
            else -> null
        }

        timer = initializeTimer(currentItem)

        _uiState.value = PracticeUIState(
            currentItem,
            false,
            getFormattedTime(currentItem?.durationInMinutes?.times(60)?.times(1000)?.toLong() ?: 0)
        )
    }


    private fun initializeTimer(currentItem: LibraryItem.PracticeItem?): CountdownTimer {
        Log.d("initializeTimer", " ${currentItem?.durationInMinutes}")

        timer = object : CountdownTimer(
            2 * 60 * 1000,
        ) {
            override fun onTick(timeRemaining: Long) {
                _uiState.value = PracticeUIState(
                    currentItem, true, getFormattedTime(timeRemaining)
                )
                Log.d("after tick", "${_uiState.value}")
            }

            override fun onFinish() {
                _uiState.value = PracticeUIState(
                    currentItem, false, getFormattedTime(
                        currentItem?.durationInMinutes?.times(60)?.times(1000)?.toLong() ?: 0
                    )
                )
            }

        }

        return timer
    }

    fun onPlayPauseClicked() {

        if (timer.isTimerRunning() && timer.isTimerPaused()) {
            timer.resume()
            _uiState.value = _uiState.value.copy(isCountdownRunning = true)
            return
        }

        if (timer.isTimerRunning()) {
            timer.pause()
            _uiState.value = _uiState.value.copy(isCountdownRunning = false)
            return
        }

        if (!timer.isTimerRunning()) {
            timer.start()
            _uiState.value = _uiState.value.copy(isCountdownRunning = true)
            return
        }

    }


    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,

            ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                override fun <T : ViewModel> create(
                    key: String, modelClass: Class<T>, handle: SavedStateHandle
                ): T {
                    @Suppress("UNCHECKED_CAST") return PracticeViewModel(
                        libraryRepository = LibraryRepository(), savedStateHandle = handle
                    ) as T
                }
            }
    }

}