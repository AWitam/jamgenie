package io.jamgenie.ui.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.R
import io.jamgenie.data.library.LibraryItem
import io.jamgenie.ui.practice.components.CountDownIndicator
import io.jamgenie.ui.practice.components.PracticeItemCheckboxList
import io.jamgenie.ui.practice.model.UIPracticeItem
import io.jamgenie.ui.previewPracticeItem1

data class PracticeItem(val name: String, val durationInSeconds: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel,
    onBackPress: () -> Unit,
    onFinishedRoutine: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    var showConfirmDialog by remember { mutableStateOf(false) }

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text(stringResource(id = R.string.practice_screen_alert_unsafe_go_back_title)) },
            text = { Text(stringResource(id = R.string.practice_screen_alert_unsafe_go_back_message)) },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmDialog = false
                    onBackPress()
                }) {
                    Text(stringResource(id = R.string.practice_screen_alert_unsafe_go_back_confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text(stringResource(id = R.string.practice_screen_alert_unsafe_go_back_dismiss))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            PracticeTopBar(
                onOptionsPress = {},
                onBackPress = { showConfirmDialog = true })
        },
    ) {

        PracticeContent(
            timeRemaining = uiState.value.timeRemaining,
            progress = uiState.value.progress,
            onPlayPause = { viewModel.onPlayPauseClicked() },
            isRunning = uiState.value.isCountdownRunning,
            currentItem = uiState.value.currentItem,
            allItems = uiState.value.allItems,
            finished = uiState.value.finished,
            onFinishedRoutine = onFinishedRoutine,
            modifier = modifier.padding(it)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeTopBar(onOptionsPress: () -> Unit, onBackPress: () -> Unit = {}) {
    TopAppBar(
        title = {},
        modifier = Modifier.background(Color.Transparent),
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onOptionsPress) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContent(
    timeRemaining: String,
    progress: Float,
    onPlayPause: () -> Unit,
    isRunning: Boolean,
    currentItem: LibraryItem? = null,
    allItems: List<UIPracticeItem> = emptyList(),
    finished: Boolean = false,
    onFinishedRoutine: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (finished) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(stringResource(id = R.string.practice_screen_alert_congratulations_title)) },
            text = { Text(stringResource(id = R.string.practice_screen_alert_congratulations_message)) },
            confirmButton = {
                TextButton(onClick = onFinishedRoutine) {
                    Text("Done".uppercase())
                }
            },
        )
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CountDownIndicator(
                progress = progress,
                timeRemaining = timeRemaining,
                onPlayPauseClick = onPlayPause,
                isRunning = isRunning
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.practice_screen_now_practicing).uppercase(),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentItem?.title ?: "",
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        PracticeItemCheckboxList(itemsList = allItems)

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PracticeScreenPreview() {
    Scaffold(
        topBar = { PracticeTopBar(onOptionsPress = {}) },
    ) {
        PracticeContent(
            timeRemaining = "02:00",
            onPlayPause = { },
            progress = 0.7f,
            currentItem = previewPracticeItem1,
            allItems = emptyList(),
            isRunning = false,
            onFinishedRoutine = {},
            modifier = Modifier.padding(it)
        )
    }

}