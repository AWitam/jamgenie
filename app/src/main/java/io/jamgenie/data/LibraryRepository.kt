package io.jamgenie.data

sealed class LibraryItem(
    open val id: String,
    open val creator: User,
    open val title: String,
    open val description: String?,
    open val level: Level?,
    open val isPublic: Boolean
) {

    data class PracticeItem(
        override val id: String,
        override val creator: User,
        override val title: String,
        override val description: String?,
        override val level: Level?,
        override val isPublic: Boolean,
        val durationInMinutes: Int,
        val image: String?,
        val video: String?,
    ) : LibraryItem(id, creator, title, description, level, isPublic)

    data class Routine(
        override val id: String,
        override val creator: User,
        override val title: String,
        override val description: String?,
        override val level: Level?,
        override val isPublic: Boolean,
        val practiceItems: List<PracticeItem>,
        val thumbnail: String?,
        val popularity: Int, // number of times this routine has been saved by users

    ) : LibraryItem(id, creator, title, description, level, isPublic)

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

    fun getPracticeItems(): List<LibraryItem.PracticeItem> {
        return LibraryDataSource().allPracticeItems.map { practiceItem ->
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
    }

    fun getRoutines(): List<LibraryItem.Routine> {
        return LibraryDataSource().allRoutines.map {
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
    }
}


