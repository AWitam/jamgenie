package io.jamgenie.data

data class PracticeItem(
    val id: String,
    val creator: User,
    val title: String,
    val description: String?,
    val level: Level?,
    val durationInMinutes: Int,
    val imageUrl: String?,
    val video: String?,
    val isPublic: Boolean
)
