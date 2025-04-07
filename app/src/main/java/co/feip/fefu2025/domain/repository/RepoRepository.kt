package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.Repo

interface RepoRepository {
    fun getStarredRepos(): Array<Repo>
    fun getPopularRepos(): Array<Repo>
}