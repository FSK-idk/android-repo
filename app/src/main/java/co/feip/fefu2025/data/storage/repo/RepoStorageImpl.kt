package co.feip.fefu2025.data.storage.repo

import co.feip.fefu2025.R
import co.feip.fefu2025.data.storage.RepoStorage
import co.feip.fefu2025.data.storage.dto.RepoDto

class RepoStorageImpl : RepoStorage {
    override fun getStarred(): Array<RepoDto> {
        return Array<RepoDto>(20) {
            RepoDto(
                name = "android-repo",
                description = "Repository for homework on android studio.",
                starNumber = 31500,
                forkNumber = 13000,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }

    override fun getPopular(): Array<RepoDto> {
        return Array<RepoDto>(20) {
            RepoDto(
                name = "android-repo",
                description = "Repository for homework on android studio.",
                starNumber = 31500,
                forkNumber = 13000,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }
}