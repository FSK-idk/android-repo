package co.feip.fefu2025.presentation.starred_repo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.loading.LoadingScreen
import co.feip.fefu2025.presentation.shared.BackTopBar
import co.feip.fefu2025.R
import androidx.compose.ui.res.stringResource
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarredRepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: StarredScreenViewModel,
) {
    val refreshState = rememberPullToRefreshState()
    val loadState = viewModel.loadState.collectAsState()
    val scrollState = viewModel.scrollState.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()
    val pageNumber = viewModel.pageNumber.collectAsState()

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
                        onBackClick = viewModel::goBack,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            ) { innerPadding ->
                PullToRefreshBox(
                    modifier = modifier.padding(innerPadding),
                    isRefreshing = false,
                    onRefresh = viewModel::refresh,
                    state = refreshState,
                ) {
                    StarredRepoListScreen(
                        scrollState = scrollState.value,
                        starredRepos = starredRepos.value,
                        pageNumber = pageNumber.value,
                        formatDecimal = viewModel::formatDecimal,
                        onRepoClick = viewModel::goToRepo,
                        onTopClick = viewModel::scrollToTop,
                        onFirstPageClick = viewModel::openFirstPage,
                        onPrevPageClick = viewModel::openPrevPage,
                        onNextPageClick = viewModel::openNextPage,
                    )
                }
            }
        }

        LoadState.Error -> {
            ErrorScreen(
                onRetryClick = viewModel::refresh,
            )
        }
    }
}