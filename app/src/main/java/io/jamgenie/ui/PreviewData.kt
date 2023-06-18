package io.jamgenie.ui

import io.jamgenie.data.library.Level
import io.jamgenie.data.library.LibraryItem
import io.jamgenie.data.home.User

val previewPracticeItem1 = LibraryItem.PracticeItem(
    id = "1",
    title = "Practice Item 1",
    description = "Practice Item 1 Description",
    durationInSeconds = 10,
    creator = User(
        role = "admin",
        username = "jake.johnson",
    ),
    level = Level.BEGINNER,
    imageUrl = "https://example.com/image.jpg",
    video = null,
    isPublic = true,
)

val previewPracticeItem2 = LibraryItem.PracticeItem(
    id = "2",
    title = "Practice Item 2",
    description = "Practice Item 2 Description",
    durationInSeconds = 20,
    creator = User(
        role = "admin",
        username = "jake.johnson",
    ),
    level = Level.INTERMEDIATE,
    imageUrl = "https://example.com/image.jpg",
    video = null,
    isPublic = true,
)

val previewItemsList = listOf(
    previewPracticeItem1,
    previewPracticeItem2,
)

val previewRoutineItemWithNoPracticeItems = LibraryItem.Routine(
    title = "Routine Title Very Long",
    description = "Routine Description lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
    imageUrl = null,
    id = "1234",
    practiceItems = emptyList(),
    popularity = 0,
    isPublic = true,
    creator = User(
        role = "admin",
        username = "jake.johnson",
    ),
    level = Level.BEGINNER,
)

val previewRoutineItemWithPracticeItems = LibraryItem.Routine(
    title = "Routine Title Very Long",
    description = "This practice routine is designed for beginners who are new to the instrument and want to develop a strong foundation. Let's get started!",
    imageUrl = null,
    id = "1234",
    practiceItems = previewItemsList,
    popularity = 0,
    isPublic = true,
    creator = User(
        role = "admin",
        username = "jake.johnson",
    ),
    level = Level.BEGINNER,
)


