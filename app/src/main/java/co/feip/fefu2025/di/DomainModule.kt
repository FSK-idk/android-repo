package co.feip.fefu2025.di

import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetPopularRepoListUseCase
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetStarredRepoListUseCase) { bind<GetStarredRepoListUseCase>() }
    factoryOf(::GetPopularRepoListUseCase) { bind<GetPopularRepoListUseCase>() }
    factoryOf(::GetRepoPageUseCase) { bind<GetRepoPageUseCase>() }
    factoryOf(::FormatDecimalUseCase) { bind<FormatDecimalUseCase>() }
}