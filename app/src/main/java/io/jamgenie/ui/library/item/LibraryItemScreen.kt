package io.jamgenie.ui.library.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.jamgenie.data.Level
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.User


/**
 * Stateful version of the Podcast player
 */
@Composable
fun LibraryItemScreen(
    viewModel: LibraryItemViewModel, onBackPress: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    LibraryItemScreen(uiState, onBackPress)
}


/**
 * Stateless version of the Player screen
 */
@Composable
private fun LibraryItemScreen(
    uiState: State<LibraryItemUIState>, onBackPress: () -> Unit, modifier: Modifier = Modifier
) {
    Surface(modifier) {
        if (uiState.value.item != null) {
            LibraryItemContent(uiState, onBackPress, modifier)
        } else {
            Text("Loading")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItemContent(
    uiState: State<LibraryItemUIState>, onBackPress: () -> Unit, modifier: Modifier = Modifier
) {


    val typography = MaterialTheme.typography

    Scaffold(topBar = { LibraryItemScreenTopBar(onBackIconClick = onBackPress) }) { it ->
        Column(
            modifier
                .fillMaxSize()
                .padding(it)
        ) {

            uiState.value.item?.let { item ->
                Text(
                    text = item.title,
                    style = typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                item.description?.let { it1 ->
                    Text(
                        text = it1,
                        style = typography.titleSmall,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }


        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItemScreenTopBar(onBackIconClick: () -> Unit) {
    TopAppBar(title = { Text(text = "Library Item") }, navigationIcon = {
        IconButton(onClick = onBackIconClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
    })

}


class SampleLibraryItemProvider : PreviewParameterProvider<LibraryItem> {
    override val values: Sequence<LibraryItem> = sequenceOf(
        LibraryItem.PracticeItem(
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

@Preview
@Composable
fun LibraryItemScreenPreview(@PreviewParameter(SampleLibraryItemProvider::class) uiState: State<LibraryItemUIState>) {
    LibraryItemScreen(uiState, onBackPress = {})
}


