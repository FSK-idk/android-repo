package co.feip.fefu2025.domain.use_case

import co.feip.fefu2025.domain.model.RepoPage
import co.feip.fefu2025.domain.repository.RepoPageRepository

class GetRepoPageUseCase(
    private val repoPageRepository: RepoPageRepository
) {
    operator fun invoke(): RepoPage {
        return repoPageRepository.getRepoPage()
    }
}