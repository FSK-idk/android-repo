package co.feip.fefu2025.presentation.starred_repo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.loading.LoadingScreen
import co.feip.fefu2025.presentation.shared.BackTopBar
import co.feip.fefu2025.R
import androidx.compose.ui.res.stringResource
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState

@Composable
fun StarredRepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: StarredScreenViewModel,
) {
    val loadState = viewModel.loadState.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }
        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
                topBar = {
                    BackTopBar(
                        title = stringResource(R.string.Starred),
                        onBackClick = viewModel::onBackClick,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            ) { innerPadding ->
                StarredRepoListScreen(
                    modifier = modifier.padding(innerPadding),
                    starredRepos = starredRepos.value,
                    formatDecimal = viewModel::formatDecimal,
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