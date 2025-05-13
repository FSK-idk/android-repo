package co.feip.fefu2025.presentation.repo_list

import android.content.Context
import android.icu.text.CompactDecimalFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.use_case.GetPopularRepoListUseCase
import co.feip.fefu2025.domain.use_case.GetRepoListByNameUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class RepoListScreenViewModel(
    private val appContext: Context,
    private val navigator: Navigator,
    private val getStarredRepoListUseCase: GetStarredRepoListUseCase,
    private val getPopularRepoListUseCase: GetPopularRepoListUseCase,
    private val getRepoListByNameUseCase: GetRepoListByNameUseCase,
) : ViewModel() {
    private val _details = RepoListScreenStates()

    val loadState = _details.loadState.asStateFlow()
    val scrollState = _details.scrollState.asStateFlow()
    val starredRepos = _details.starredRepos.asStateFlow()
    val popularRepos = _details.popularRepos.asStateFlow()
    val pageNumber = _details.pageNumber.asStateFlow()

    private val _searchDetails = SearchTopBarStates()

    val searchLoadState = _searchDetails.loadState.asStateFlow()
    val searchScrollState = _searchDetails.scrollState.asStateFlow()
    val searchQuery = _searchDetails.query.asStateFlow()
    val searchRepos = _searchDetails.repos.asStateFlow()
    val searchPageNumber = _searchDetails.pageNumber.asStateFlow()

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            _searchDetails.query
                .debounce(1000L)
                .collect {
                    _searchDetails.pageNumber.value = 1
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        loadSearchData()
                    }
                }
        }

        refresh()
    }


//  repo list screen


    private suspend fun loadData() {
        try {
            _details.loadState.value = LoadState.Loading

            _details.starredRepos.value = getStarredRepoListUseCase(
                perPage = 10,
                pageNumber = 1
            )
            _details.popularRepos.value = getPopularRepoListUseCase(
                perPage = 20,
                pageNumber = _details.pageNumber.value
            )

            _details.loadState.value = LoadState.NotLoading
        } catch (e: Exception) {
            _details.loadState.value = LoadState.Error
        }
    }

    fun refresh() {
        viewModelScope.launch {
            loadData()
        }
    }


//  repo list screen navigation


    fun scrollToTop() {
        viewModelScope.launch {
            _details.scrollState.value.scrollToItem(0)
        }
    }

    fun openFirstPage() {
        if (_details.pageNumber.value != 1) {
            _details.pageNumber.value = 1
            refresh()
            scrollToTop()
        }
    }

    fun openPrevPage() {
        if (_details.pageNumber.value != 1) {
            _details.pageNumber.value -= 1
            refresh()
            scrollToTop()
        }
    }

    fun openNextPage() {
        _details.pageNumber.value += 1
        refresh()
        scrollToTop()
    }


//  search top bar


    private suspend fun loadSearchData() {
        if (_searchDetails.query.value.isNotEmpty()) {
            try {
                _searchDetails.loadState.value = LoadState.Loading

                _searchDetails.repos.value =
                    getRepoListByNameUseCase(
                        name = _searchDetails.query.value,
                        perPage = 20,
                        pageNumber = _searchDetails.pageNumber.value
                    )

                _searchDetails.loadState.value = LoadState.NotLoading
            } catch (e: Exception) {
                _searchDetails.loadState.value = LoadState.Error
            }
        } else {
            _searchDetails.repos.value = listOf()
        }
    }

    fun refreshSearch() {
        viewModelScope.launch {
            loadSearchData()
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchDetails.query.value = query
    }


//  search top bar navigation


    fun scrollSearchToTop() {
        viewModelScope.launch {
            _searchDetails.scrollState.value.scrollToItem(0)
        }
    }

    fun openSearchFirstPage() {
        if (_searchDetails.pageNumber.value != 1) {
            _searchDetails.pageNumber.value = 1
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                refreshSearch()
                scrollSearchToTop()
            }
        }
    }

    fun openSearchPrevPage() {
        if (_searchDetails.pageNumber.value != 1) {
            _searchDetails.pageNumber.value -= 1
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                refreshSearch()
                scrollSearchToTop()
            }
        }
    }

    fun openSearchNextPage() {
        _searchDetails.pageNumber.value += 1
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            refreshSearch()
            scrollSearchToTop()
        }
    }


//  screen navigation


    fun goToStarred() {
        viewModelScope.launch {
            navigator.navigate(Destination.StarredRepoListScreen)
        }
    }

    fun goToRepo(repoId: Int) {
        viewModelScope.launch {
            navigator.navigate(Destination.RepoPageScreen(repoId))
        }
    }


//  misc


    fun formatDecimal(number: Int): String {
        return CompactDecimalFormat.getInstance(
            appContext.resources.configuration.locales[0],
            CompactDecimalFormat.CompactStyle.SHORT
        ).format(number)
    }
}