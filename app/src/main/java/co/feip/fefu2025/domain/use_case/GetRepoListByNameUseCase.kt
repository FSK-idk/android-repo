package co.feip.fefu2025.domain.use_case

import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.repository.RepoListRepository

class GetRepoListByNameUseCase(
    private val repoListRepository: RepoListRepository
) {
    suspend operator fun invoke(name: String, perPage: Int, pageNumber: Int): List<Repo> {
        return repoListRepository.getRepoListByName(name, perPage, pageNumber)
    }
}