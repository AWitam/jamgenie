package io.jamgenie.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.R
import io.jamgenie.data.home.LogItem

@Composable
fun RecentActivity(items: List<LogItem>?, modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.home_screen_current_recent_activity),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = modifier.height(16.dp))
        Box(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)

                .fillMaxWidth()
        ) {
            if (items.isNullOrEmpty()) {
                Text(
                    text = stringResource(id = R.string.home_screen_current_recent_activity_empty),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.height(16.dp))
            } else {
                items.forEach { item ->
                    RecentActivityItem(item)
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun RecentActivityItem(item: LogItem) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = item.routineTitle)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.outline_timer_24),
                contentDescription = "Timer icon",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item.timePracticed)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = "Timer icon",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item.date)
        }
    }

}

@Preview
@Composable
fun RecentActivityPreview() {
    RecentActivity(
        items = listOf(
            LogItem("Routine Title", "1h 30m", "Today"),
            LogItem("Routine Title", "1h 30m", "Today")
        )
    )
}
