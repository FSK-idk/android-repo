package co.feip.fefu2025.data.storage

import co.feip.fefu2025.data.storage.dto.RepoDto

interface RepoListStorage {
    suspend fun getStarredList(perPage: Int, pageNumber: Int): List<RepoDto>

    suspend fun getPopularList(perPage: Int, pageNumber: Int): List<RepoDto>

    suspend fun getRepoListByName(name: String, perPage: Int, pageNumber: Int): List<RepoDto>
}