package co.feip.fefu2025.di

import co.feip.fefu2025.data.repository.RepoPageRepositoryImpl
import co.feip.fefu2025.data.repository.RepoRepositoryImpl
import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.RepoStorage
import co.feip.fefu2025.data.storage.repo.RepoStorageImpl
import co.feip.fefu2025.data.storage.repo_page.RepoPageStorageImpl
import co.feip.fefu2025.domain.repository.RepoPageRepository
import co.feip.fefu2025.domain.repository.RepoRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RepoPageStorageImpl) { bind<RepoPageStorage>() }
    singleOf(::RepoPageRepositoryImpl) { bind<RepoPageRepository>() }
    singleOf(::RepoStorageImpl) { bind<RepoStorage>() }
    singleOf(::RepoRepositoryImpl) { bind<RepoRepository>() }
}