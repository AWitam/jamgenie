package io.jamgenie.data.library

import io.jamgenie.data.home.User

data class PracticeItem(
    val id: String,
    val creator: User,
    val title: String,
    val description: String?,
    val level: Level?,
    val durationInSeconds: Int,
    val imageUrl: String?,
    val video: String?,
    val isPublic: Boolean
)
