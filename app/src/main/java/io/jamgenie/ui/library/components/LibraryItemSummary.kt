package io.jamgenie.ui.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.data.Level
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.User
import io.jamgenie.ui.utils.getLibraryItemSummary

@Composable
fun LibraryItemSummary(item: LibraryItem, spacerWidth: Int = 8, modifier: Modifier = Modifier) {
    val itemSummary = getLibraryItemSummary(item)
    val typography = MaterialTheme.typography

    Spacer(modifier = Modifier.height(spacerWidth.dp))
    Row(
        Modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (itemSummary.totalPracticeItems !== null) {
            Text(
                text = "${itemSummary.totalPracticeItems} items",
                style = typography.labelSmall,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Divider(
                modifier = Modifier
                    .height(12.dp)
                    .width(1.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (itemSummary.durationInMinutes !== null) {
            Text(
                text = itemSummary.durationInMinutes,
                style = typography.labelSmall,
            )
        }

        if (item.level !== null) {
            Spacer(modifier = Modifier.width(8.dp))
            Divider(
                modifier = Modifier
                    .height(12.dp)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = removeUnderScores(item.level.toString()),
                style = typography.labelSmall,

                )

        }
    }
    Spacer(modifier = Modifier.height(spacerWidth.dp))


}

fun removeUnderScores(text: String): String {
    return text.replace("_", " ")
}

@Preview
@Composable
fun LibraryItemSummaryPreview() {
    LibraryItemSummary(
        item = LibraryItem.Routine(
            title = "Routine Title Very Long",
            description = "Routine Description Very Long Very Very long",
            imageUrl = null,
            id = "1234",
            practiceItems = emptyList(),
            popularity = 0,
            isPublic = true,
            creator = User(
                role = "admin",
                username = "jake.johnson",
            ),
            level = Level.BEGINNER,
        )
    )
}