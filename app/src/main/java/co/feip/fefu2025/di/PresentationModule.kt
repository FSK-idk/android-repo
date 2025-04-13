package co.feip.fefu2025.di

import co.feip.fefu2025.presentation.repo_list.RepoListScreenViewModel
import co.feip.fefu2025.presentation.repo_page.RepoPageScreenViewModel
import co.feip.fefu2025.presentation.starred_repo_list.StarredScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::RepoListScreenViewModel)
    viewModelOf(::RepoPageScreenViewModel)
    viewModelOf(::StarredScreenViewModel)
}