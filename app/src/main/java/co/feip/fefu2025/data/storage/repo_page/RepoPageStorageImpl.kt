package co.feip.fefu2025.data.storage.repo_page

import co.feip.fefu2025.Constants
import co.feip.fefu2025.R
import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.dto.LangDto
import co.feip.fefu2025.data.storage.dto.RepoPageDto
import kotlinx.datetime.LocalDate

class RepoPageStorageImpl: RepoPageStorage {
    override fun get(): RepoPageDto {
        return RepoPageDto(
            name = "android-repo",
            description = "Repository for homework on android studio.",
            starNumber = 31500,
            forkNumber = 13000,
            creationDate = LocalDate(2025, 3, 3),
            langs = arrayOf(
                LangDto("Kotlin", 85.0f, Constants.Companion.languageColor.getValue("Kotlin")),
                LangDto("Python", 10f, Constants.Companion.languageColor.getValue("Python")),
                LangDto("C++", 3f, Constants.Companion.languageColor.getValue("C++")),
                LangDto("Lua", 2f, Constants.Companion.languageColor.getValue("Lua")),
            ),
            icon = R.drawable.ic_launcher_foreground,
        )
    }
}