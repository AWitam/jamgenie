package io.jamgenie.ui.practice.model

data class UIPracticeItem(
    val id : String,
    val title: String,
    val description: String,
    val durationInMinutes: Int,
    var isChecked: Boolean, // readonly checkbox
    val isHighlighted: Boolean = false,
)
