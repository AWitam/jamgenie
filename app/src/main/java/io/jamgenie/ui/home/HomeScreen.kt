package io.jamgenie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.jamgenie.R
import io.jamgenie.ui.common.AppBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(bottomBar = { AppBottomBar(navController) }) { it ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
        ) {
            Text(text = stringResource(id = R.string.home_screen_title))
        }

    }
}
