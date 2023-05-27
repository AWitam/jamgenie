package io.jamgenie.ui.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel(), modifier: Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        TabRow(selectedTabIndex = uiState.selectedTab.ordinal) {
            LibraryTab.values().forEach { tab ->
                Tab(
                    selected = uiState.selectedTab == tab,
                    onClick = { viewModel.onTabSelected(tab) },
                    text = { Text(text = formatTabName(tab.name)) }
                )
            }
        }
    }
}

fun formatTabName(str: String): String {
    return str.lowercase().replaceFirstChar { it -> it.uppercase() }.replace("_", " ")
}

