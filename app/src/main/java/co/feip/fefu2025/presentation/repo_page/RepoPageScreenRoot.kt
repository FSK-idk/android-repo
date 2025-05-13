package co.feip.fefu2025.presentation.repo_page

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.loading.LoadingScreen
import co.feip.fefu2025.presentation.shared.BackTopBar
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState

@Composable
fun RepoScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RepoPageScreenViewModel,
) {
    val loadState = viewModel.loadState.collectAsState()
    val descriptionExpanded = viewModel.descriptionExpanded.collectAsState()
    val repoName = viewModel.repoName.collectAsState()
    val repoDescription = viewModel.repoDescription.collectAsState()
    val repoStarNumber = viewModel.repoStarNumber.collectAsState()
    val repoForkNumber = viewModel.repoForkNumber.collectAsState()
    val repoCreationDate = viewModel.repoCreationDate.collectAsState()
    val repoLangs = viewModel.repoLangs.collectAsState()
    val repoIconUrl = viewModel.repoIconUrl.collectAsState()
    val repoStarred = viewModel.repoStarred.collectAsState()

    when (loadState.value) {
        LoadState.Loading -> {
            LoadingScreen()
        }

        LoadState.NotLoading -> {
            Scaffold(
                modifier = modifier,
                topBar = {
                    BackTopBar(
                        title = repoName.value,
                        onBackClick = viewModel::goBack,
                    )
                },
                floatingActionButton = {
                    StarButton(
                        starred = repoStarred.value,
                        onClick = viewModel::starRepo,
                    )
                }
            ) { innerPadding ->
                RepoPageScreen(
                    modifier = Modifier.padding(innerPadding),
                    isDescriptionExpanded = descriptionExpanded.value,
                    repoName = repoName.value,
                    repoDescription = repoDescription.value,
                    repoStarNumber = repoStarNumber.value,
                    repoForkNumber = repoForkNumber.value,
                    repoCreationDate = repoCreationDate.value,
                    repoLangs = repoLangs.value,
                    repoIconUrl = repoIconUrl.value,
                    turnDescription = viewModel::turnDescription,
                    formatDecimal = viewModel::formatDecimal,
                )
            }
        }

        LoadState.Error -> {
            ErrorScreen(
                onRetryClick = viewModel::refresh,
            )
        }
    }
}