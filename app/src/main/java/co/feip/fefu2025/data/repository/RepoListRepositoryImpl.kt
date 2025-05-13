package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.dto.toDomain
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoListRepository

class RepoListRepositoryImpl(
    private val repoListStorage: RepoListStorage
) : RepoListRepository {
    override suspend fun getStarredRepoList(
        perPage: Int,
        pageNumber: Int
    ): List<Repo> = repoListStorage.getStarredList(perPage, pageNumber).map { it.toDomain }

    override suspend fun getPopularRepoList(
        perPage: Int,
        pageNumber: Int
    ): List<Repo> = repoListStorage.getPopularList(perPage, pageNumber).map { it.toDomain }

    override suspend fun getRepoListByName(
        name: String,
        perPage: Int,
        pageNumber: Int
    ): List<Repo> = repoListStorage.getRepoListByName(name, perPage, pageNumber).map { it.toDomain }
}