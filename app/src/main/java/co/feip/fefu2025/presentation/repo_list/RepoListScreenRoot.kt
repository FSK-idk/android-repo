package co.feip.fefu2025.presentation.repo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState
import co.feip.fefu2025.presentation.loading.LoadingScreen

@Composable
fun RepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RepoListScreenViewModel,
) {
    val loadState = viewModel.loadState.collectAsState()
    val searchLoadState = viewModel.searchLoadState.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()
    val popularRepos = viewModel.popularRepos.collectAsState()
    val searchedQuery = viewModel.searchQuery.collectAsState()
    val searchedRepos = viewModel.searchRepos.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }

        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
                topBar = {
                    SearchTopBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        loadState = searchLoadState.value,
                        query = searchedQuery.value,
                        onQueryChange = viewModel::onSearchedQueryChange,
                        searchRepos = searchedRepos.value,
                        onRepoClick = viewModel::onRepoClick,
                        formatDecimal = viewModel::formatDecimal,
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

        LoadState.Error -> {
            ErrorScreen(
                onRetryClick = viewModel::onRetryClick,
            )
        }
    }
}