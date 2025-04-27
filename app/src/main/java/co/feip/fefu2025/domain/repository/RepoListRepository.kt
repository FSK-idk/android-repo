package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.Repo

interface RepoListRepository {
    suspend fun getStarredRepoList(): List<Repo>
    suspend fun getPopularRepoList(): List<Repo>
    suspend fun getRepoListByName(name: String): List<Repo>
}