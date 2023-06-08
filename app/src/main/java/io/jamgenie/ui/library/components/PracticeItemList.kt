package io.jamgenie.ui.library.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.Level
import io.jamgenie.data.User


@Composable
fun PracticeItemsList(
    practiceItems: List<LibraryItem.PracticeItem>,
    modifier: Modifier = Modifier
) {

    Box() {

        Column() {
            practiceItems.forEach { item ->
                Spacer(modifier = modifier.padding(4.dp))
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        LibraryItemCard(
                            item = item,
                            isClickable = false,
                            onCardClick = {},
                            modifier = modifier

                        )
                    }
                }
                Spacer(modifier = modifier.padding(4.dp))
            }
        }
    }
}

@Composable
@Preview
fun PracticeItemsListPreview() {
    PracticeItemsList(
        practiceItems = listOf(
            LibraryItem.PracticeItem(
                id = "1",
                title = "Practice Item 1",
                description = "Practice Item 1 Description",
                durationInMinutes = 10,
                creator = User(
                    role = "admin",
                    username = "jake.johnson",
                ),
                level = Level.BEGINNER,
                imageUrl = "https://example.com/image.jpg",
                video = null,
                isPublic = true,
            ),
            LibraryItem.PracticeItem(
                id = "2",
                title = "Practice Item 2",
                description = "Practice Item 2 Description",
                durationInMinutes = 20,
                creator = User(
                    role = "admin",
                    username = "jake.johnson",
                ),
                level = Level.INTERMEDIATE,
                imageUrl = "https://example.com/image.jpg",
                video = null,
                isPublic = true,
            ),
        )
    )
}


