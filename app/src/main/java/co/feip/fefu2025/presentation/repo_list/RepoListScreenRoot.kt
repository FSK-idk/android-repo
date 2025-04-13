package co.feip.fefu2025.presentation.repo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.shared.LoadingScreen

@Composable
fun RepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RepoListScreenViewModel,
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()
    val popularRepos = viewModel.popularRepos.collectAsState()

    if (isLoading.value) {
        LoadingScreen()
    } else {
        Scaffold(
            modifier = modifier,
            topBar = {
                SearchTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            },
        ) { innerPadding ->
            RepoListScreen(
                modifier = Modifier.padding(innerPadding),
                starredRepos = starredRepos.value,
                popularRepos = popularRepos.value,
                formatDecimal = viewModel::formatDecimal,
                onStarredClick = viewModel::onStarredClick,
                onRepoClick = viewModel::onRepoClick,
            )
        }
    }
}