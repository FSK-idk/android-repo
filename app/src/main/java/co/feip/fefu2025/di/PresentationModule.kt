package co.feip.fefu2025.di

import co.feip.fefu2025.presentation.main_screen.MainScreenViewModel
import co.feip.fefu2025.presentation.repo_screen.RepoScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::RepoScreenViewModel)
}