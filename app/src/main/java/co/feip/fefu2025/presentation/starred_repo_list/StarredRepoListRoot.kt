package co.feip.fefu2025.presentation.starred_repo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.shared.LoadingScreen
import co.feip.fefu2025.presentation.shared.BackTopBar
import co.feip.fefu2025.R
import androidx.compose.ui.res.stringResource

@Composable
fun StarredRepoListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: StarredScreenViewModel,
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val starredRepos = viewModel.starredRepos.collectAsState()

    if (isLoading.value) {
        LoadingScreen()
    } else {
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
}