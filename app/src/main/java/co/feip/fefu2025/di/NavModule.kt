package co.feip.fefu2025.di

import co.feip.fefu2025.nav.DefaultNavigator
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import org.koin.dsl.module

val navModule = module {
    single<Navigator> {
        DefaultNavigator(Destination.RepoListScreen)
    }
}