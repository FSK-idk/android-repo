package co.feip.fefu2025.data.storage

import co.feip.fefu2025.data.storage.dto.RepoDto

interface RepoStorage {
    fun getStarred(): Array<RepoDto>
    fun getPopular(): Array<RepoDto>
}