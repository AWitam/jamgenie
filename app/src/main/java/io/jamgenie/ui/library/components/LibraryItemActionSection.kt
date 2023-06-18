package io.jamgenie.ui.library.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.R
import io.jamgenie.data.LibraryItem
import io.jamgenie.ui.previewRoutineItemWithNoPracticeItems

enum class LibraryItemActionType {
    PRACTICE,
    SAVE_TO_ROUTINES,
    SAVE_AND_EDIT
}

data class RoutineItemAction(
    val action: LibraryItemActionType,
    val icon: Int,
    @StringRes val label: Int,
)

val ROUTINE_ACTIONS = listOf(
    RoutineItemAction(
        action = LibraryItemActionType.PRACTICE,
        icon = R.drawable.baseline_play_arrow_24,
        label = R.string.library_item_action_practice
    ),
    RoutineItemAction(
        action = LibraryItemActionType.SAVE_TO_ROUTINES,
        icon = R.drawable.baseline_bookmark_add_24,
        label = R.string.library_item_action_save_to_routines
    ),
    RoutineItemAction(
        action = LibraryItemActionType.SAVE_AND_EDIT,
        icon = R.drawable.baseline_mode_edit_24,
        label = R.string.library_item_action_save_and_edit
    )
)

@Composable
fun LibraryItemActionSection(
    item: LibraryItem,
    navigateToPractice: (itemId: String) -> Unit,
    modifier: Modifier = Modifier
) {

    val actions = when (item) {
        is LibraryItem.Routine ->
            ROUTINE_ACTIONS

        is LibraryItem.PracticeItem -> emptyList()
    }


    Box() {

        Column() {
            actions.forEach {
                Spacer(modifier = modifier.padding(4.dp))
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable(onClick = { navigateToPractice(item.id) })
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = it.icon),
                            contentDescription = stringResource(id = it.label),
                            modifier = modifier.padding(16.dp),
                        )
                        Text(
                            text = stringResource(id = it.label),
                        )
                    }
                }
                Spacer(modifier = modifier.padding(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun LibraryItemActionSectionPreview() {
    LibraryItemActionSection(
        item = previewRoutineItemWithNoPracticeItems,
        navigateToPractice = {},
    )
}