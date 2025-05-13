package co.feip.fefu2025.di

import co.feip.fefu2025.domain.use_case.GetPopularRepoListUseCase
import co.feip.fefu2025.domain.use_case.GetRepoListByNameUseCase
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.domain.use_case.StarRepoUseCase
import co.feip.fefu2025.domain.use_case.UnstarRepoUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetStarredRepoListUseCase) { bind<GetStarredRepoListUseCase>() }
    factoryOf(::GetPopularRepoListUseCase) { bind<GetPopularRepoListUseCase>() }
    factoryOf(::GetRepoListByNameUseCase) { bind<GetRepoListByNameUseCase>() }
    factoryOf(::GetRepoPageUseCase) { bind<GetRepoPageUseCase>() }
    factoryOf(::StarRepoUseCase) { bind<StarRepoUseCase>() }
    factoryOf(::UnstarRepoUseCase) { bind<UnstarRepoUseCase>() }
}