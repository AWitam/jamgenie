package io.jamgenie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.jamgenie.ui.common.AppBottomBar
import io.jamgenie.ui.home.components.CurrentPractice
import io.jamgenie.ui.home.components.Greeting
import io.jamgenie.ui.home.components.RecentActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onStartCurrentRoutine: (itemId: String) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBarr() },
        bottomBar = { AppBottomBar(navController) }) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = it.calculateTopPadding(), horizontal = 16.dp)
        ) {

            Greeting(userName = uiState.user?.username ?: "")
            Spacer(modifier = Modifier.padding(24.dp))
            CurrentPractice(
                currentPracticeTitle = uiState.currentPracticeRoutineTitle,
                onStartPractice = {
                    uiState.user!!.currentPracticeRoutineId?.let { it1 ->
                        onStartCurrentRoutine(
                            it1
                        )
                    }


                }
            )
            Spacer(modifier = Modifier.padding(24.dp))
            RecentActivity(items = uiState.user?.recentActivity)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBarr() {
    TopAppBar(title = {}, actions = {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Person, contentDescription = "Localized description")
        }

    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current), onStartCurrentRoutine = {})
}
