package io.jamgenie.ui.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.jamgenie.data.LibraryItem
import io.jamgenie.R
import io.jamgenie.ui.previewPracticeItem2
import io.jamgenie.ui.previewRoutineItemWithPracticeItems
import io.jamgenie.ui.utils.formatMinSec

@Composable
fun <T : LibraryItem> LibraryItemCard(
    item: T,
    displayCreator: Boolean = true,
    displayFullSummary: Boolean = true,
    displayDuration: Boolean = false,
    isClickable: Boolean = true,
    onCardClick: (itemId: String) -> Unit,
    modifier: Modifier = Modifier
) {

    val typography = MaterialTheme.typography

    val cardModifier = when (isClickable) {
        true -> modifier.clickable(onClick = { onCardClick(item.id) })
        false -> modifier
    }


    Card(
        modifier = cardModifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.height(IntrinsicSize.Min)
        ) {
            Box(modifier = modifier.fillMaxHeight()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image_placeholder),
                    contentDescription = item.description,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(100.dp)
                        .fillMaxHeight()

                )
            }
            Column(modifier = modifier.padding(8.dp)) {
                Text(
                    text = item.title, style = typography.titleMedium
                )

                if (displayDuration && item is LibraryItem.PracticeItem) Text(
                    text = formatMinSec(item.durationInSeconds),
                    style = typography.labelSmall
                )

                if (displayCreator) Text(
                    text = "@" + item.creator.username,
                    style = typography.labelSmall,
                )

                if (displayFullSummary) {
                    LibraryItemSummary(
                        item = item,
                        modifier = modifier.fillMaxWidth()
                    )
                }

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
        onCardClick = {}, item = previewRoutineItemWithPracticeItems, displayFullSummary = true
    )
}


@Preview
@Composable
fun PracticeItemCardPreview() {
    LibraryItemCard(
        onCardClick = {}, item = previewPracticeItem2
    )
}