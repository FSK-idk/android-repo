package co.feip.fefu2025.presentation.repo_list

import androidx.compose.foundation.lazy.LazyListState
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow

data class SearchTopBarStates(
    val loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.NotLoading),
    val scrollState: MutableStateFlow<LazyListState> = MutableStateFlow(LazyListState()),
    val query: MutableStateFlow<String> = MutableStateFlow(""),
    val repos: MutableStateFlow<List<Repo>> = MutableStateFlow(listOf()),
    val pageNumber: MutableStateFlow<Int> = MutableStateFlow(1),
)