package io.jamgenie.data

data class Routine(
    val id: String,
    val creator: User,
    val title: String,
    val description: String?,
    val practiceItems: List<PracticeItem>,
    val level: Level?,
    val imageUrl: String?,
    val popularity : Int, // number of times this routine has been saved by users
    val isPublic: Boolean
)
