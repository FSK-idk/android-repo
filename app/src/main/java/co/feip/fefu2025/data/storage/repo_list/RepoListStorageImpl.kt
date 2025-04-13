package co.feip.fefu2025.data.storage.repo_list

import co.feip.fefu2025.R
import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.dto.RepoDto
import kotlinx.coroutines.delay

class RepoListStorageImpl : RepoListStorage {
    override suspend fun getStarredList(): List<RepoDto> {
        delay(1000L) // for example
        return List<RepoDto>(20) { index ->
            RepoDto(
                id = index,
                name = "android-repo $index",
                description = "Repository for homework on android studio.",
                starNumber = 31500,
                forkNumber = 13000,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }

    override suspend fun getPopularList(): List<RepoDto> {
        delay(1000L) // for example
        return List<RepoDto>(20) { index ->
            RepoDto(
                id = index,
                name = "android-repo $index",
                description = "Repository for homework on android studio.",
                starNumber = 31500,
                forkNumber = 13000,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }
}