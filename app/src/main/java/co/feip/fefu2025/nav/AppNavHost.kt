package co.feip.fefu2025.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import co.feip.fefu2025.presentation.repo_list.RepoListScreenRoot
import co.feip.fefu2025.presentation.repo_list.RepoListScreenViewModel
import co.feip.fefu2025.presentation.repo_page.RepoPageScreenViewModel
import co.feip.fefu2025.presentation.repo_page.RepoScreenRoot
import co.feip.fefu2025.presentation.starred_repo_list.StarredRepoListScreenRoot
import co.feip.fefu2025.presentation.starred_repo_list.StarredScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navHostController.navigate(
                action.destination,
            ) {
                action.navOptions(this)
            }

            is NavigationAction.NavigateUp -> navHostController.navigateUp()
        }
    }

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = navigator.startDestination,
    ) {
        composable<Destination.RepoListScreen> {
            val viewModel = koinViewModel<RepoListScreenViewModel>()
            RepoListScreenRoot(viewModel = viewModel)
        }

        composable<Destination.StarredRepoListScreen> {
            val viewModel = koinViewModel<StarredScreenViewModel>()
            StarredRepoListScreenRoot(viewModel = viewModel)
        }

        composable<Destination.RepoPageScreen>(
            deepLinks = listOf(
                navDeepLink<Destination.RepoPageScreen>(
                    basePath = "mysuperapp://repo"
                )
            )
        ) {
            val entry = it.toRoute<Destination.RepoPageScreen>()
            val viewModel = koinViewModel<RepoPageScreenViewModel> { parametersOf(entry) }
            RepoScreenRoot(viewModel = viewModel)
        }
    }
}