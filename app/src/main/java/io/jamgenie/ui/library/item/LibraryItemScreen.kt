package io.jamgenie.ui.library.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.jamgenie.R
import io.jamgenie.data.Level
import io.jamgenie.data.LibraryItem
import io.jamgenie.data.User
import io.jamgenie.ui.library.components.LibraryItemActionSection
import io.jamgenie.ui.library.components.LibraryItemCard
import io.jamgenie.ui.library.components.LibraryItemSummary
import io.jamgenie.ui.utils.getLibraryItemSummary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItemScreen(
    viewModel: LibraryItemViewModel, onBackPress: () -> Unit, modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { LibraryItemScreenTopBar(onBackIconClick = onBackPress) },
    ) {
        if (uiState.value.item != null) {
            LibraryItemContent(uiState.value.item!!, onBackPress, it, modifier)
        } else {
            Text("Loading")
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItemContent(
    item: LibraryItem,
    onBackPress: () -> Unit,
    scaffoldPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val itemSummary = getLibraryItemSummary(item)
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier
            .fillMaxSize()
            .padding(
                top = scaffoldPadding.calculateTopPadding(),
                end = 16.dp,
                start = 16.dp,
                bottom = scaffoldPadding.calculateBottomPadding()
            )
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max)

        ) {
            Image(
                painter = painterResource(id = R.drawable.user_placeholder),
                contentDescription = "${item.creator.username} profile picture",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(CircleShape)
                    .height(32.dp)
                    .width(32.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Column {
                Text(text = "@${item.creator.username}", style = typography.labelMedium)
                Text(text = item.creator.role, style = typography.labelSmall)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image_placeholder),
                    contentDescription = item.description,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, colorScheme.secondary),
                                startY = 0f,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        }
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

                Text(
                    text = item.title.uppercase(),
                    style = typography.titleLarge,
                    color = colorScheme.onSecondary,
                    modifier = modifier.padding(top = 16.dp)

                )

            }
            item.description?.let {
                Text(
                    text = it,
                    style = typography.bodyMedium,
                    modifier = modifier.padding(top = 16.dp)
                )
            }
            LibraryItemSummary(item = item, modifier = modifier.fillMaxWidth())
            Spacer(modifier = modifier.height(16.dp))
            LibraryItemActionSection(item = item)
            Spacer(modifier = modifier.height(16.dp))
            if (itemSummary.totalPracticeItems !== null && itemSummary.totalPracticeItems > 0) {

                Text(
                    text = stringResource(id = R.string.library_item_items_list),
                    style = typography.titleMedium
                )
                Spacer(modifier = modifier.height(16.dp))
                Column {
                    (item as LibraryItem.Routine).practiceItems.forEachIndexed { index, practiceItem ->
                        LibraryItemCard(
                            item = practiceItem,
                            isClickable = false,
                            displayCreator = false,
                            displayFullSummary = false,
                            displayDuration = true,
                            onCardClick = {},
                            modifier = modifier
                        )
                    }


                }
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryItemScreenTopBar(onBackIconClick: () -> Unit) {
    TopAppBar(title = {},
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackIconClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }

        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LibraryItemScreenPreview() {
    Scaffold(topBar = { LibraryItemScreenTopBar(onBackIconClick = {}) }) {
        LibraryItemContent(
            item = LibraryItem.Routine(
                title = "Routine Title Very Long",
                description = "This practice routine is designed for beginners who are new to the instrument and want to develop a strong foundation. Let's get started!",
                imageUrl = null,
                id = "1234",
                practiceItems = listOf(
                    LibraryItem.PracticeItem(
                        title = "Practice Item Title Very Long",
                        description = "Practice Item Description Very Long Very Very long",
                        imageUrl = null,
                        id = "1234",
                        video = null,
                        isPublic = true,
                        creator = User(
                            role = "admin",
                            username = "jake.johnson",
                        ),
                        level = Level.BEGINNER,
                        durationInMinutes = 10
                    ),
                    LibraryItem.PracticeItem(
                        title = "Practice Item Title Very Long",
                        description = "Practice Item Description Very Long Very Very long",
                        imageUrl = null,
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
                ),
                popularity = 0,
                isPublic = true,
                creator = User(
                    role = "admin",
                    username = "jake.johnson",
                ),
                level = Level.BEGINNER,
            ), scaffoldPadding = it, onBackPress = {})
    }
}


