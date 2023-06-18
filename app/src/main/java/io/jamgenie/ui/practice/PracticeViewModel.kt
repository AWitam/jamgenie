package io.jamgenie.ui.practice


import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.jamgenie.data.library.LibraryItem
import io.jamgenie.data.library.LibraryRepository
import io.jamgenie.ui.practice.model.UIPracticeItem
import io.jamgenie.ui.utils.CountdownTimer
import io.jamgenie.ui.utils.getFormattedTime
import io.jamgenie.ui.utils.secToMs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class PracticeUIState(
    val currentItem: LibraryItem.PracticeItem? = null,
    val allItems: List<UIPracticeItem> = emptyList(),
    val isCountdownRunning: Boolean = false,
    val timeRemaining: String,
    val progress: Float = 100f,
    val finished: Boolean = false
)


class PracticeViewModel(
    savedStateHandle: SavedStateHandle, private val libraryRepository: LibraryRepository
) : ViewModel() {
    private val libraryItemId: String = checkNotNull(savedStateHandle["itemId"])
    private val routinePracticeItems = libraryRepository.getRoutinePracticeItems(libraryItemId)
    private var currentItem = mutableStateOf(routinePracticeItems[0])
    private var currentItemDurationInMs =
        mutableStateOf(secToMs(currentItem.value.durationInSeconds))

    private val allItems = mutableStateOf(mapToAllUIItems(routinePracticeItems))

    private val _uiState = MutableStateFlow(
        PracticeUIState(
            currentItem = currentItem.value,
            allItems = allItems.value,
            timeRemaining = getFormattedTime(currentItemDurationInMs.value)
        )
    )
    val uiState: StateFlow<PracticeUIState> = _uiState.asStateFlow()

    private var timer: CountdownTimer

    init {
        timer = initializeTimer()
    }


    private fun initializeTimer(): CountdownTimer {
        timer = object : CountdownTimer(
            currentItemDurationInMs.value
        ) {
            override fun onTick(timeRemaining: Long) {
                if (timeRemaining == 0L) {
                    onFinish()
                    return
                }
                val progressValue = (timeRemaining.toFloat() / currentItemDurationInMs.value)

                _uiState.value = _uiState.value.copy(
                    currentItem = currentItem.value,
                    timeRemaining = getFormattedTime(timeRemaining),
                    progress = progressValue
                )

            }

            override fun onFinish() {
                val finishedItem = currentItem.value
                val finishedItemIndex = allItems.value.indexOfFirst { it.id == finishedItem.id }
                val nextItemIndex = finishedItemIndex + 1
                allItems.value =
                    allItems.value.map { if (it.id === finishedItem.id) it.copy(isChecked = true) else it }
                if (nextItemIndex >= allItems.value.size) {
                    _uiState.value = _uiState.value.copy(
                        currentItem = null,
                        finished = true,
                        allItems = allItems.value,
                        isCountdownRunning = false,
                        progress = 0f,
                        timeRemaining = "00:00"
                    )
                    return
                }

                currentItem.value = routinePracticeItems[nextItemIndex]
                currentItemDurationInMs.value = secToMs(currentItem.value.durationInSeconds)
                allItems.value =
                    allItems.value.map { if (it.id === finishedItem.id) it.copy(isChecked = true) else it }

                _uiState.value = PracticeUIState(
                    currentItem = currentItem.value,
                    allItems = allItems.value,
                    false,
                    getFormattedTime(currentItemDurationInMs.value),
                )
                timer = initializeTimer()

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

    private fun mapToAllUIItems(items: List<LibraryItem.PracticeItem>): List<UIPracticeItem> {
        return items.map { practiceItem ->
            UIPracticeItem(
                id = practiceItem.id,
                title = practiceItem.title,
                description = practiceItem.description ?: "",
                durationInMinutes = practiceItem.durationInSeconds,
                isChecked = false,
            )
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


