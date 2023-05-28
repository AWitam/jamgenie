package io.jamgenie.ui.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.jamgenie.R
import io.jamgenie.data.LibraryItem
import io.jamgenie.ui.components.LibraryItemCard
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.jamgenie.ui.components.AppBottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    navController: NavController,
    viewModel: LibraryViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = { LibraryTopBar() }, bottomBar = { AppBottomBar(navController) }) { it ->

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
        ) {
            LibraryTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { viewModel.onTabSelected(it) }
            )
            LibraryList(listItems = uiState.listItems)

        }

    }


}

fun formatTabName(str: String): String {
    return str.lowercase().replaceFirstChar { it -> it.uppercase() }.replace("_", " ")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryTopBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.library_screen_title)) },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Person, contentDescription = "Localized description")
            }

        }
    )
}

@Composable
fun LibraryTabRow(
    selectedTab: LibraryTab,
    onTabSelected: (LibraryTab) -> Unit,
) {
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        TabRow(selectedTabIndex = selectedTab.ordinal) {
            LibraryTab.values().forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { onTabSelected(tab) },
                    text = { Text(text = formatTabName(tab.name)) }
                )
            }
        }

    }
}

@Composable
fun LibraryList(
    modifier: Modifier = Modifier,
    listItems: List<LibraryItem>,
) {
    LazyColumn(modifier = modifier) {
        listItems.forEach { item ->
            item {
                LibraryItemCard(item = item)
            }
        }
    }
}


@Preview
@Composable
fun LibraryScreenPreview() {
    LibraryScreen(navController = rememberNavController())
}