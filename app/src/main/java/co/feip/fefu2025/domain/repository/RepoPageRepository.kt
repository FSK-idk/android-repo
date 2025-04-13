package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepoPage

interface RepoPageRepository {
    suspend fun getRepoPage(repoId: Int): RepoPage
}