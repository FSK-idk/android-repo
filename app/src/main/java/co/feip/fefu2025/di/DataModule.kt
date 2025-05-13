package co.feip.fefu2025.di

import co.feip.fefu2025.data.repository.RepoPageRepositoryImpl
import co.feip.fefu2025.data.repository.RepoListRepositoryImpl
import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.repo_list.RepoListStorageImpl
import co.feip.fefu2025.data.service.gitlab_api.GitLabApi
import co.feip.fefu2025.data.storage.repo_page.RepoPageStorageImpl
import co.feip.fefu2025.domain.repository.RepoPageRepository
import co.feip.fefu2025.domain.repository.RepoListRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::GitLabApi) { bind<GitLabApi>() }
    singleOf(::RepoPageStorageImpl) { bind<RepoPageStorage>() }
    singleOf(::RepoPageRepositoryImpl) { bind<RepoPageRepository>() }
    singleOf(::RepoListStorageImpl) { bind<RepoListStorage>() }
    singleOf(::RepoListRepositoryImpl) { bind<RepoListRepository>() }
}