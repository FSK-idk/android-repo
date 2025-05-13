package co.feip.fefu2025.data.storage

import co.feip.fefu2025.data.storage.dto.RepoPageDto

interface RepoPageStorage {
    suspend fun get(id: Int): RepoPageDto

    suspend fun star(id: Int): Boolean

    suspend fun unstar(id: Int): Boolean
}