package co.feip.fefu2025.presentation.repo_page

import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate

data class RepoPageScreenStates(
    val loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.NotLoading),
    val descriptionExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val repoName: MutableStateFlow<String> = MutableStateFlow(""),
    val repoDescription: MutableStateFlow<String> = MutableStateFlow(""),
    val repoStarNumber: MutableStateFlow<Int> = MutableStateFlow(0),
    val repoForkNumber: MutableStateFlow<Int> = MutableStateFlow(0),
    val repoCreationDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.fromEpochDays(0)),
    val repoLangs: MutableStateFlow<List<Lang>> = MutableStateFlow(listOf()),
    val repoStarred: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val repoIconUrl: MutableStateFlow<String> = MutableStateFlow(""),
)