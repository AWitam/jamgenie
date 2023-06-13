package io.jamgenie.ui.practice

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class PracticeItem(val name: String, val durationInSeconds: Int)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PracticeScreen(viewModel: PracticeViewModel, modifier: Modifier = Modifier) {
    val uiState = viewModel.uiState.collectAsState()

    val buttonText = if (uiState.value.isCountdownRunning) "Pause" else "Play"

    PracticeContent(
        countdownText = uiState.value.countdownText,
        onPlayPause = { viewModel.onPlayPauseClicked() },
        buttonText = buttonText,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeTopBar(onOptionsPress: () -> Unit) {
    TopAppBar(title = {}, actions = {
        IconButton(onClick = onOptionsPress) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContent(
    countdownText: String,
    onPlayPause: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { PracticeTopBar(onOptionsPress = {}) },
    ) { it ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth().padding(it)
        ) {
            Text(text = countdownText, modifier = Modifier.padding(it))
            Button(onClick = onPlayPause) {
                Text(text = buttonText, modifier = Modifier.padding(8.dp))
            }
        }
    }

}


@Preview
@Composable
fun PracticeScreenPreview() {
    PracticeContent(countdownText = "02:00", onPlayPause = { }, buttonText = "Play")
}