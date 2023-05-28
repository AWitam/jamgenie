package io.jamgenie.data

class LibraryDataSource {
    val user1 = User("john_doe", "admin")
    val user2 = User("stacy", "admin")
    val user3 = User("jane_smith", "artist")


    private val practiceItem1 = PracticeItem(
        id = "PI_1",
        creator = user1,
        title = "Finger stretches",
        description = "Stretch your fingers to improve your finger dexterity.",
        durationInMinutes = 2,
        isPublic = true,
        level = null,
        image = null,
        video = null
    )


    private val practiceItem2 = PracticeItem(
        id = "PI_2",
        creator = user1,
        title = "Major chord changes",
        description = "Pick two major chords and practice changing between them. Example: C major and G major. or D major and A major.",
        durationInMinutes = 2,
        isPublic = true,
        level = Level.BRAND_NEW,
        image = null,
        video = null
    )


    private val practiceItem3 = PracticeItem(
        id = "PI_3",
        creator = user1,
        title = "Come as you are riff",
        description = "Practice the riff from the song Come as you are by Nirvana.",
        durationInMinutes = 5,
        isPublic = true,
        level = Level.BRAND_NEW,
        image = null,
        video = null
    )

    private val practiceItem4 = PracticeItem(
        id = "PI_4",
        creator = user1,
        title = "Song work",
        description = "Practice the song you are currently working on.",
        durationInMinutes = 10,
        isPublic = true,
        level = null,
        image = null,
        video = null
    )

    private val practiceItem5 = PracticeItem(
        id = "PI_5",
        creator = user1,
        title = "C major scale",
        description = "Practice the C major scale in the first position.",
        durationInMinutes = 2,
        isPublic = true,
        level = Level.BEGINNER,
        image = null,
        video = null
    )

    private val practiceItem6 = PracticeItem(
        id = "PI_6",
        creator = user1,
        title = "Sus chord changes",
        description = "Work on your sus chords changes. Example: C major and C sus4. or D sus2 and D sus4.",
        durationInMinutes = 2,
        isPublic = true,
        level = Level.BEGINNER,
        image = null,
        video = null
    )

    private val practiceItem7 = PracticeItem(
        id = "PI_7",
        creator = user1,
        title = "Day tripper riff",
        description = "Practice the riff from the song Day tripper by The Beatles.",
        durationInMinutes = 5,
        isPublic = true,
        level = Level.BEGINNER,
        image = null,
        video = null
    )

    private val brandNewLevelRoutine = Routine(
        id = "R_1",
        creator = user1,
        title = "Brand new level routine",
        description = "This routine is for brand new guitarists. It will help you get started.",
        practiceItems = listOf(
            practiceItem1,
            practiceItem2,
            practiceItem2,
            practiceItem3,
            practiceItem4
        ),
        level = Level.BRAND_NEW,
        thumbnail = null,
        popularity = 0,
        isPublic = true
    )


    private val beginnerLevelRoutine = Routine(
        id = "R_2",
        creator = user1,
        title = "Beginner level routine",
        description = "This routine is for beginner guitarists.",
        practiceItems = listOf(
            practiceItem1,
            practiceItem5,
            practiceItem6,
            practiceItem7,
            practiceItem4
        ),
        level = Level.BEGINNER,
        thumbnail = null,
        popularity = 0,
        isPublic = true
    )

    val allPracticeItems = listOf(
        practiceItem1,
        practiceItem2,
        practiceItem3,
        practiceItem4,
        practiceItem5,
        practiceItem6,
        practiceItem7
    )

    val allRoutines = listOf(
        brandNewLevelRoutine,
        beginnerLevelRoutine
    )

}