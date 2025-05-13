package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.Repo

interface RepoListRepository {
    suspend fun getStarredRepoList(perPage: Int, pageNumber: Int): List<Repo>

    suspend fun getPopularRepoList(perPage: Int, pageNumber: Int): List<Repo>

    suspend fun getRepoListByName(name: String, perPage: Int, pageNumber: Int): List<Repo>
}