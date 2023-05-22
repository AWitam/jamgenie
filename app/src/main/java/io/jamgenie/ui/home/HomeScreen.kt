package io.jamgenie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.jamgenie.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = stringResource(R.string.home_screen_title))
    }
}
