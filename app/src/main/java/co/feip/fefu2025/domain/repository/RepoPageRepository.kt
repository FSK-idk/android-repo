package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepoPage

interface RepoPageRepository {
    suspend fun getRepoPage(repoId: Int): RepoPage

    suspend fun starRepo(repoId: Int): Boolean

    suspend fun unstarRepo(repoId: Int): Boolean
}