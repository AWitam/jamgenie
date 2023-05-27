package io.jamgenie.data

sealed class LibraryItem {
    data class PracticeItem(
        val id: String,
        val creator: User,
        val title: String,
        val description: String?,
        val level: Level?,
        val durationInMinutes: Int,
        val image: String?,
        val video: String?,
        val isPublic: Boolean
    ) : LibraryItem()

    data class Routine(
        val id: String,
        val creator: User,
        val title: String,
        val description: String?,
        val practiceItems: List<PracticeItem>,
        val level: Level?,
        val thumbnail: String?,
        val popularity: Int, // number of times this routine has been saved by users
        val isPublic: Boolean
    ) : LibraryItem()
}


class LibraryRepository {

    fun getAllLibraryItems(): List<LibraryItem> {
        val allPracticeItems = LibraryDataSource().allPracticeItems.map { practiceItem ->
            LibraryItem.PracticeItem(
                id = practiceItem.id,
                creator = practiceItem.creator,
                title = practiceItem.title,
                description = practiceItem.description,
                level = practiceItem.level,
                durationInMinutes = practiceItem.durationInMinutes,
                image = practiceItem.image,
                video = practiceItem.video,
                isPublic = practiceItem.isPublic
            )
        }

        val allRoutines = LibraryDataSource().allRoutines.map {
            LibraryItem.Routine(
                id = it.id,
                creator = it.creator,
                title = it.title,
                description = it.description,
                level = it.level,
                thumbnail = it.thumbnail,
                isPublic = it.isPublic,
                popularity = it.popularity,
                practiceItems = it.practiceItems.map { practiceItem ->
                    LibraryItem.PracticeItem(
                        id = practiceItem.id,
                        creator = practiceItem.creator,
                        title = practiceItem.title,
                        description = practiceItem.description,
                        level = practiceItem.level,
                        durationInMinutes = practiceItem.durationInMinutes,
                        image = practiceItem.image,
                        video = practiceItem.video,
                        isPublic = practiceItem.isPublic
                    )
                }

            )
        }

        return allPracticeItems + allRoutines
    }
}


