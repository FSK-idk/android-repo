package co.feip.fefu2025.domain.use_case

import co.feip.fefu2025.domain.model.RepoPage
import co.feip.fefu2025.domain.repository.RepoPageRepository

class UnstarRepoUseCase(
    private val repoPageRepository: RepoPageRepository
) {
    suspend operator fun invoke(repoId: Int): Boolean {
        return repoPageRepository.unstarRepo(repoId)
    }
}