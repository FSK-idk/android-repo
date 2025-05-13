package co.feip.fefu2025.domain.use_case

import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoListRepository

class GetStarredRepoListUseCase(
    private val repoListRepository: RepoListRepository
) {
    suspend operator fun invoke(perPage: Int, pageNumber: Int): List<Repo> {
        return repoListRepository.getStarredRepoList(perPage, pageNumber)
    }
}