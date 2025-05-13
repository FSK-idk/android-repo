package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.dto.toDomain
import co.feip.fefu2025.domain.model.RepoPage
import co.feip.fefu2025.domain.repository.RepoPageRepository

class RepoPageRepositoryImpl(
    private val repoPageStorage: RepoPageStorage
) : RepoPageRepository {
    override suspend fun getRepoPage(repoId: Int): RepoPage = repoPageStorage.get(repoId).toDomain

    override suspend fun starRepo(repoId: Int): Boolean = repoPageStorage.star(repoId)

    override suspend fun unstarRepo(repoId: Int): Boolean = repoPageStorage.unstar(repoId)
}