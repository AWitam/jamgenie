package io.jamgenie.ui.practice.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.jamgenie.ui.utils.formatMinSec

@Composable
fun ItemWithCheckbox(
    title: String,
    description: String,
    durationInSeconds: Int,
    isChecked: Boolean,
    isHighlighted: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = if (isHighlighted) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = null, // readonly checkbox
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.secondary,
                    disabledUncheckedColor = MaterialTheme.colorScheme.secondary,
                    disabledCheckedColor = MaterialTheme.colorScheme.secondary,
                ),
                enabled = false,
            )
            Spacer(modifier = modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = formatMinSec(durationInSeconds),
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(modifier = modifier.height(4.dp))
                Text(text = description, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            }
        }
    }
}

@Preview
@Composable
fun ItemWithCheckboxPreview() {
    var isChecked by remember { mutableStateOf(false) }
    ItemWithCheckbox(
        title = "Practice item title",
        description = "Description",
        durationInSeconds = 120,
        isChecked = isChecked,
    )
}