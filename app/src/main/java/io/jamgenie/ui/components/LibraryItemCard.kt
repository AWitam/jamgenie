package io.jamgenie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.Level
import io.jamgenie.data.User

@Composable
fun <T : LibraryItem> LibraryItemCard(item: T) {

    val duration = when (item) {
        is LibraryItem.Routine -> item.practiceItems.sumOf { it.durationInMinutes }
            .toString() + " min"

        is LibraryItem.PracticeItem -> item.durationInMinutes.toString() + " min"
        else -> null
    }

    val totalPracticeItems = when (item) {
        is LibraryItem.Routine -> item.practiceItems.size
        else -> null
    }

    val typography = MaterialTheme.typography

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.onBackground)
                    .width(100.dp)
            ) {
                Text(
                    text = "Thumbnail",
                    modifier = Modifier.align(Alignment.Center),
                    style = typography.labelSmall
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.title, style = typography.titleMedium
                )

                Text(
                    text = "@" + item.creator.username,
                    style = typography.labelSmall,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    Modifier.height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (totalPracticeItems !== null) {
                        Text(
                            text = "$totalPracticeItems items",
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
                    if (duration !== null) {
                        Text(
                            text = duration,
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
                            text = item.level!!.name,
                            style = typography.labelSmall,
                            modifier = Modifier.weight(1f)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description ?: "",
                    style = typography.bodySmall,
                    maxLines = 2,
                )

            }
        }
    }
}


@Preview
@Composable
fun RoutineRoutineCardPreview() {

    LibraryItemCard(
        item = LibraryItem.Routine(
            title = "Routine Title Very Long",
            description = "Routine Description Very Long Very Very long",
            thumbnail = null,
            id = "1234",
            practiceItems = emptyList(),
            popularity = 0,
            isPublic = true,
            creator = User(
                role = "admin",
                username = "jake.johnson",
            ),
            level = Level.BEGINNER
        )
    )


}


@Preview
@Composable
fun PracticeItemCardPreview() {

    LibraryItemCard(
        item = LibraryItem.PracticeItem(
            title = "Practice Item Title Very Long",
            description = "Practice Item Description Very Long Very Very long",
            image = null,
            id = "1234",
            video = null,
            isPublic = true,
            creator = User(
                role = "admin",
                username = "jake.johnson",
            ),
            level = Level.BEGINNER,
            durationInMinutes = 10
        )
    )
}