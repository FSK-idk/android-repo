package co.feip.fefu2025.presentation.repo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetPopularRepoListUseCase
import co.feip.fefu2025.domain.use_case.GetRepoListByNameUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RepoListScreenViewModel(
    private val navigator: Navigator,
    private val getStarredRepoListUseCase: GetStarredRepoListUseCase,
    private val getPopularRepoListUseCase: GetPopularRepoListUseCase,
    private val getRepoListByNameUseCase: GetRepoListByNameUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val loadState = _loadState.asStateFlow()

    private val _searchLoadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val searchLoadState = _searchLoadState.asStateFlow()

    private val _starredRepos = MutableStateFlow<List<Repo>>(listOf())
    val starredRepos = _starredRepos.asStateFlow()

    private val _popularRepos = MutableStateFlow<List<Repo>>(listOf())
    val popularRepos = _popularRepos.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchRepos = MutableStateFlow<List<Repo>>(listOf())

    @OptIn(FlowPreview::class)
    val searchRepos = searchQuery
        .debounce(1000L)
        .combine(_searchRepos) { query, repos ->
            if (query.isNotEmpty()) {
                try {
                    _searchLoadState.value = LoadState.Loading

                    val loadedRepos = getRepoListByNameUseCase(query)

                    _searchLoadState.value = LoadState.NotLoading

                    loadedRepos
                } catch (e: Exception) {
                    _searchLoadState.value = LoadState.Error
                    repos
                }
            } else {
                repos
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _searchRepos.value
        )

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading

                _starredRepos.value = getStarredRepoListUseCase()
                _popularRepos.value = getPopularRepoListUseCase()

                _loadState.value = LoadState.NotLoading
            } catch (e: Exception) {
                _loadState.value = LoadState.Error
            }
        }
    }

    fun formatDecimal(number: Int): String {
        return formatDecimalUseCase(number)
    }

    fun onSearchedQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onStarredClick() {
        viewModelScope.launch {
            navigator.navigate(Destination.StarredRepoListScreen)
        }
    }

    fun onRetryClick() {
        loadData()
    }

    fun onRepoClick(repoId: Int) {
        viewModelScope.launch {
            navigator.navigate(Destination.RepoPageScreen(repoId))
        }
    }
}