package co.feip.fefu2025.data.storage

import co.feip.fefu2025.data.storage.dto.RepoDto

interface RepoListStorage {
    suspend fun getStarredList(): List<RepoDto>
    suspend fun getPopularList(): List<RepoDto>
}