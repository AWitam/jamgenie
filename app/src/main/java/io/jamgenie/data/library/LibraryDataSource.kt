package io.jamgenie.data.library

import io.jamgenie.data.home.User

class LibraryDataSource {
    val user1 = User("john_doe", "admin")
    val user2 = User("kate_m", "admin")

    val imageUrl1 = "https://images.pexels.com/photos/3987251/pexels-photo-3987251.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    val imageUrl2 = "https://images.pexels.com/photos/2157171/pexels-photo-2157171.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"

    private val practiceItem1 = PracticeItem(
        id = "PI_1",
        creator = user1,
        title = "Finger stretches",
        description = "Stretch your fingers to improve your finger dexterity.",
        durationInSeconds = 30,
        isPublic = true,
        level = null,
        imageUrl = "https://images.pexels.com/photos/4426320/pexels-photo-4426320.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        video = null
    )


    private val practiceItem2 = PracticeItem(
        id = "PI_2",
        creator = user2,
        title = "Major chord changes",
        description = "Pick two major chords and practice changing between them. Example: C major and G major. or D major and A major.",
        durationInSeconds = 60,
        isPublic = true,
        level = Level.BRAND_NEW,
        imageUrl = "https://images.pexels.com/photos/374698/pexels-photo-374698.jpeg?auto=compress&cs=tinysrgb&w=800&h=600&dpr=1",
        video = null
    )


    private val practiceItem3 = PracticeItem(
        id = "PI_3",
        creator = user1,
        title = "Come as you are riff",
        description = "Practice the riff from the song Come as you are by Nirvana.",
        durationInSeconds = 120,
        isPublic = true,
        level = Level.BRAND_NEW,
        imageUrl = "https://images.pexels.com/photos/1933589/pexels-photo-1933589.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        video = null
    )

    private val practiceItem4 = PracticeItem(
        id = "PI_4",
        creator = user1,
        title = "Song work",
        description = "Practice the song you are currently working on.",
        durationInSeconds = 600,
        isPublic = true,
        level = null,
        imageUrl = "https://images.pexels.com/photos/8182363/pexels-photo-8182363.jpeg",
        video = null
    )

    private val practiceItem5 = PracticeItem(
        id = "PI_5",
        creator = user1,
        title = "C major scale",
        description = "Practice the C major scale in the first position.",
        durationInSeconds = 120,
        isPublic = true,
        level = Level.BEGINNER,
        imageUrl = imageUrl1,
        video = null
    )

    private val practiceItem6 = PracticeItem(
        id = "PI_6",
        creator = user2,
        title = "Sus chord changes",
        description = "Work on your sus chords changes. Example: C major and C sus4. or D sus2 and D sus4.",
        durationInSeconds = 120,
        isPublic = true,
        level = Level.BEGINNER,
        imageUrl = imageUrl1,
        video = null
    )

    private val practiceItem7 = PracticeItem(
        id = "PI_7",
        creator = user1,
        title = "Day tripper riff",
        description = "Practice the riff from the song Day tripper by The Beatles.",
        durationInSeconds = 120,
        isPublic = true,
        level = Level.BEGINNER,
        imageUrl = imageUrl1,
        video = null
    )

    val practiceItem8 = PracticeItem(
        id = "PI_8",
        creator = user1,
        title = "Strumming Patterns",
        description = "Practice different strumming patterns to improve your rhythm skills.",
        durationInSeconds = 300,
        isPublic = true,
        level = Level.BEGINNER,
        imageUrl = imageUrl2,
        video = null
    )

    val practiceItem9 = PracticeItem(
        id = "PI_9",
        creator = user1,
        title = "Chord Progressions",
        description = "Practice common chord progressions to enhance your chord transitioning skills.",
        durationInSeconds = 240,
        isPublic = true,
        level = Level.INTERMEDIATE,
        imageUrl = imageUrl2,
        video = null,
    )

    val practiceItem10 = PracticeItem(
        id = "PI_3",
        creator = user2,
        title = "Picking Exercises",
        description = "Improve your picking technique with a variety of picking exercises.",
        durationInSeconds = 45,
        isPublic = true,
        level = Level.BEGINNER,
        imageUrl = "https://images.pexels.com/photos/4088014/pexels-photo-4088014.jpeg?auto=compress&cs=tinysrgb&w=800",
        video = null
    )

    val practiceItem11= PracticeItem(
        id = "PI_11",
        creator = user2,
        title = "Barre Chord Practice",
        description = "Develop strength and accuracy in playing barre chords with targeted exercises.",
        durationInSeconds = 90,
        isPublic = true,
        level = Level.INTERMEDIATE,
        imageUrl = "https://images.pexels.com/photos/4472065/pexels-photo-4472065.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        video = null
    )

    val practiceItem12 = PracticeItem(
        id = "PI_12",
        creator = user1,
        title = "Scales Mastery",
        description = "Master major and minor scales across the fretboard with scale exercises.",
        durationInSeconds = 1200,
        isPublic = true,
        level = Level.ADVANCED,
        imageUrl = "https://images.pexels.com/photos/4426320/pexels-photo-4426320.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        video = null
    )

    val practiceItem13 = PracticeItem(
        id = "PI_6",
        creator = user2,
        title = "Fingerpicking Patterns",
        description = "Learn and practice popular fingerpicking patterns to enhance your fingerstyle playing.",
        durationInSeconds = 240,
        isPublic = true,
        level = Level.INTERMEDIATE,
        imageUrl = "https://images.pexels.com/photos/7462024/pexels-photo-7462024.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        video = null
    )

    val practiceItem14 = PracticeItem(
        id = "PI_14",
        creator = user1,
        title = "Strumming Techniques",
        description = "Explore various strumming techniques to add dynamics and rhythm to your playing.",
        durationInSeconds = 45,
        isPublic = true,
        level = Level.INTERMEDIATE,
        imageUrl = "https://images.pexels.com/photos/7462024/pexels-photo-7462024.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
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
            practiceItem3,
            practiceItem4
        ),
        level = Level.BRAND_NEW,
        imageUrl = imageUrl2,
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
        imageUrl = "https://images.pexels.com/photos/12616312/pexels-photo-12616312.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        popularity = 0,
        isPublic = true
    )

    val chordsRoutine = Routine(
        id = "R_3",
        creator = user2,
        title = "Fundamental Chords Practice",
        description = "This routine focuses on practicing fundamental chords for beginner guitarists.",
        practiceItems = listOf(
            practiceItem3,
            practiceItem7,
            practiceItem8,
            practiceItem10
        ),
        level = Level.BEGINNER,
        imageUrl = "https://images.pexels.com/photos/374698/pexels-photo-374698.jpeg?auto=compress&cs=tinysrgb&w=800&h=600&dpr=1",
        popularity = 0,
        isPublic = true
    )

    val fingerstyleRoutine = Routine(
        id = "R_4",
        creator = user2,
        title = "Fingerstyle Techniques",
        description = "This routine introduces basic fingerstyle techniques for beginner guitarists.",
        practiceItems = listOf(
            practiceItem1,
            practiceItem5,
            practiceItem8,
            practiceItem11,
            practiceItem10
        ),
        level = Level.INTERMEDIATE,
        imageUrl = "https://images.pexels.com/photos/4472065/pexels-photo-4472065.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
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
        chordsRoutine,
        fingerstyleRoutine,
        beginnerLevelRoutine
    )

}