package co.feip.fefu2025.di

import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetPopularReposUseCase
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import co.feip.fefu2025.domain.use_case.GetStarredReposUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetStarredReposUseCase) { bind<GetStarredReposUseCase>() }
    factoryOf(::GetPopularReposUseCase) { bind<GetPopularReposUseCase>() }
    factoryOf(::GetRepoPageUseCase) { bind<GetRepoPageUseCase>() }
    factoryOf(::FormatDecimalUseCase) { bind<FormatDecimalUseCase>() }
}