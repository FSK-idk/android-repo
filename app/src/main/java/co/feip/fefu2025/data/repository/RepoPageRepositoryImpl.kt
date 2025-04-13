package co.feip.fefu2025.data.repository

import androidx.compose.ui.graphics.Color
import co.feip.fefu2025.data.storage.dto.RepoPageDto
import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.domain.model.RepoPage
import co.feip.fefu2025.domain.repository.RepoPageRepository

class RepoPageRepositoryImpl(
    private val repoPageStorage: RepoPageStorage
) : RepoPageRepository {
    override suspend fun getRepoPage(repoId: Int): RepoPage {
        return mapToDomain(repoPageStorage.get(repoId))
    }

    private fun mapToDomain(dto: RepoPageDto): RepoPage {
        return RepoPage(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            starNumber = dto.starNumber,
            forkNumber = dto.forkNumber,
            creationDate = dto.creationDate,
            langs = dto.langs.map {
                Lang(
                    name = it.name,
                    percentage = it.percentage,
                    color = Color(it.color),
                )
            }.toTypedArray(),
            icon = dto.icon,
        )
    }
}