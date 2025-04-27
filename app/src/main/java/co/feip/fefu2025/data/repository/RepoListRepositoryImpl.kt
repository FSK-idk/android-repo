package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.dto.RepoDto
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoListRepository

class RepoListRepositoryImpl(
    private val repoListStorage: RepoListStorage
) : RepoListRepository {
    override suspend fun getStarredRepoList(): List<Repo> {
        return repoListStorage.getStarredList().map { mapToDomain(it) }
    }

    override suspend fun getPopularRepoList(): List<Repo> {
        return repoListStorage.getPopularList().map { mapToDomain(it) }
    }

    override suspend fun getRepoListByName(name: String): List<Repo> {
        return repoListStorage.getRepoListByName(name).map { mapToDomain(it) }
    }

    fun mapToDomain(dto: RepoDto): Repo {
        return Repo(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            starNumber = dto.starNumber,
            forkNumber = dto.forkNumber,
            icon = dto.icon,
        )
    }
}