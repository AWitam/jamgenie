package io.jamgenie.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.R

@Composable
fun CurrentPractice(
    currentPracticeTitle: String?, onStartPractice: () -> Unit, modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.home_screen_current_practice),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Column() {
                Text(text = currentPracticeTitle ?: "", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onStartPractice
                    ) {
                        Text(text = stringResource(id = R.string.home_screen_current_practice_start).uppercase())
                    }
                }

            }

        }
    }

}

@Preview
@Composable
fun CurrentPracticePreview() {
    CurrentPractice(currentPracticeTitle = "Practice Title", onStartPractice = {})
}