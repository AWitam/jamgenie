package io.jamgenie.ui.library

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.jamgenie.R

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = stringResource(R.string.library_screen_title))
    }
}