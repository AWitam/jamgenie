package io.jamgenie.ui.practice.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.jamgenie.R
import io.jamgenie.ui.practice.model.UIPracticeItem


@Composable
fun PracticeItemCheckboxList(itemsList: List<UIPracticeItem>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.practice_screen_items_in_routine).uppercase(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))

        itemsList.forEach { item ->
            ItemWithCheckbox(
                title = item.title,
                description = item.description,
                isChecked = item.isChecked,
                durationInSeconds = item.durationInMinutes,
                modifier = modifier

            )
            Spacer(modifier = modifier.height(4.dp))
        }
    }
}