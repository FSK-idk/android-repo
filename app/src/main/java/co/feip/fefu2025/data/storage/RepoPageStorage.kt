package co.feip.fefu2025.data.storage

import co.feip.fefu2025.data.storage.dto.RepoPageDto

interface RepoPageStorage {
    suspend fun get(id: Int) : RepoPageDto
}