package co.feip.fefu2025.presentation.repo_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState
import co.feip.fefu2025.presentation.loading.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RepoListScreenViewModel,
) {
    val refreshState = rememberPullToRefreshState()

    val loadState = viewModel.loadState.collectAsState()
    val scrollState = viewModel.scrollState.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()
    val popularRepos = viewModel.popularRepos.collectAsState()
    val pageNumber = viewModel.pageNumber.collectAsState()

    val searchLoadState = viewModel.searchLoadState.collectAsState()
    val searchScrollState = viewModel.searchScrollState.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()
    val searchRepos = viewModel.searchRepos.collectAsState()
    val searchPageNumber = viewModel.searchPageNumber.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }

        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
                topBar = {
                    SearchTopBar(
                        loadState = searchLoadState.value,
                        scrollState = searchScrollState.value,
                        query = searchQuery.value,
                        onQueryChange = viewModel::onSearchQueryChange,
                        searchRepos = searchRepos.value,
                        pageNumber = searchPageNumber.value,
                        onRepoClick = viewModel::goToRepo,
                        formatDecimal = viewModel::formatDecimal,
                        onTopClick = viewModel::scrollSearchToTop,
                        onFirstPageClick = viewModel::openSearchFirstPage,
                        onPrevPageClick = viewModel::openSearchPrevPage,
                        onNextPageClick = viewModel::openSearchNextPage,
                    )
                },
            ) { innerPadding ->
                PullToRefreshBox(
                    modifier = Modifier.padding(innerPadding),
                    isRefreshing = false,
                    onRefresh = viewModel::refresh,
                    state = refreshState,
                ) {
                    RepoListScreen(
                        scrollState = scrollState.value,
                        starredRepos = starredRepos.value,
                        popularRepos = popularRepos.value,
                        pageNumber = pageNumber.value,
                        formatDecimal = viewModel::formatDecimal,
                        onStarredClick = viewModel::goToStarred,
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