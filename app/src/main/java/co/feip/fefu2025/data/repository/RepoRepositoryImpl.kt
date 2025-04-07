package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.storage.RepoStorage
import co.feip.fefu2025.data.storage.dto.RepoDto
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoRepository

class RepoRepositoryImpl(
    private val repoStorage: RepoStorage
) : RepoRepository {
    override fun getStarredRepos(): Array<Repo> {
        return repoStorage.getStarred().map { mapToDomain(it) }.toTypedArray()
    }

    override fun getPopularRepos(): Array<Repo> {
        return repoStorage.getPopular().map { mapToDomain(it) }.toTypedArray()
    }

    fun mapToDomain(dto: RepoDto): Repo {
        return Repo(
            name = dto.name,
            description = dto.description,
            starNumber = dto.starNumber,
            forkNumber = dto.forkNumber,
            icon = dto.icon,
        )
    }
}