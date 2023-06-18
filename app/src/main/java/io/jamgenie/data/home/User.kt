package io.jamgenie.data.home


data class User(
    val username: String,
    val role: String,
    var currentPracticeRoutineId: String? = null,
    var recentActivity: List<LogItem> = emptyList()
)