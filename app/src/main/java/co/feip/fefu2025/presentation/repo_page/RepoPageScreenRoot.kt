package co.feip.fefu2025.presentation.repo_page

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.shared.LoadingScreen
import co.feip.fefu2025.presentation.shared.BackTopBar

@Composable
fun RepoScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RepoPageScreenViewModel,
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val isDescriptionExpanded = viewModel.isDescriptionExpanded.collectAsState()
    val repoName = viewModel.repoName.collectAsState()
    val repoDescription = viewModel.repoDescription.collectAsState()
    val repoStarNumber = viewModel.repoStarNumber.collectAsState()
    val repoForkNumber = viewModel.repoForkNumber.collectAsState()
    val repoCreationDate = viewModel.repoCreationDate.collectAsState()
    val repoLangs = viewModel.repoLangs.collectAsState()
    val repoIcon = viewModel.repoIcon.collectAsState()

    if (isLoading.value) {
        LoadingScreen()
    } else {
        Scaffold(
            modifier = modifier,
            topBar = {
                BackTopBar(
                    title = repoName.value,
                    onBackClick = viewModel::onBackClick,
                )
            },
        ) { innerPadding ->
            RepoPageScreen(
                modifier = Modifier.padding(innerPadding),
                isDescriptionExpanded = isDescriptionExpanded.value,
                repoName = repoName.value,
                repoDescription = repoDescription.value,
                repoStarNumber = repoStarNumber.value,
                repoForkNumber = repoForkNumber.value,
                repoCreationDate = repoCreationDate.value,
                repoLangs = repoLangs.value,
                repoIcon = repoIcon.value,
                turnDescription = viewModel::turnDescription,
                formatDecimal = viewModel::formatDecimal,
            )
        }
    }
}