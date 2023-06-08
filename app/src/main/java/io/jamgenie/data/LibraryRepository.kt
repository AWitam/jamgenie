package io.jamgenie.data

sealed class LibraryItem(
    open val id: String,
    open val creator: User,
    open val title: String,
    open val description: String?,
    open val level: Level?,
    open val imageUrl: String?,
    open val isPublic: Boolean
) {

    data class PracticeItem(
        override val id: String,
        override val creator: User,
        override val title: String,
        override val description: String?,
        override val level: Level?,
        override val isPublic: Boolean,
        override val imageUrl: String?,
        val durationInMinutes: Int,
        val video: String?,
    ) : LibraryItem(id, creator, title, description, level, imageUrl, isPublic)

    data class Routine(
        override val id: String,
        override val creator: User,
        override val title: String,
        override val description: String?,
        override val level: Level?,
        override val isPublic: Boolean,
        override val imageUrl: String?,
        val practiceItems: List<PracticeItem>,
        val popularity: Int, // number of times this routine has been saved by users

    ) : LibraryItem(id, creator, title, description, level, imageUrl, isPublic)

}


class LibraryRepository {

    fun getAllPracticeItems(): List<LibraryItem.PracticeItem> {
        return LibraryDataSource().allPracticeItems.map { practiceItem ->
            LibraryItem.PracticeItem(
                id = practiceItem.id,
                creator = practiceItem.creator,
                title = practiceItem.title,
                description = practiceItem.description,
                level = practiceItem.level,
                durationInMinutes = practiceItem.durationInMinutes,
                imageUrl = practiceItem.imageUrl,
                video = practiceItem.video,
                isPublic = practiceItem.isPublic
            )
        }
    }

    fun getAllRoutines(): List<LibraryItem.Routine> {
        return LibraryDataSource().allRoutines.map {
            LibraryItem.Routine(
                id = it.id,
                creator = it.creator,
                title = it.title,
                description = it.description,
                level = it.level,
                imageUrl = it.imageUrl,
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
                        imageUrl = practiceItem.imageUrl,
                        video = practiceItem.video,
                        isPublic = practiceItem.isPublic
                    )
                }

            )
        }
    }

    fun getLibraryItem(libraryItemId: String): LibraryItem {
        val allPracticeItems = getAllPracticeItems()
        val allRoutines = getAllRoutines()

        return allPracticeItems.find { it.id == libraryItemId }
            ?: allRoutines.find { it.id == libraryItemId }!!

    }
}

