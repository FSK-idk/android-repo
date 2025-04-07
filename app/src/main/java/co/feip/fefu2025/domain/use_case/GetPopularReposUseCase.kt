package co.feip.fefu2025.domain.use_case

import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoRepository

class GetPopularReposUseCase(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(): Array<Repo> {
        return repoRepository.getPopularRepos()
    }
}