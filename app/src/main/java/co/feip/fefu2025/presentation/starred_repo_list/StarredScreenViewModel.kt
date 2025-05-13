package co.feip.fefu2025.presentation.starred_repo_list

import android.content.Context
import android.icu.text.CompactDecimalFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StarredScreenViewModel(
    private val appContext: Context,
    private val navigator: Navigator,
    private val getStarredRepoListUseCase: GetStarredRepoListUseCase,
) : ViewModel() {
    private val _details = StarredRepoListScreenStates()

    val loadState = _details.loadState.asStateFlow()
    val scrollState = _details.scrollState.asStateFlow()
    val starredRepos = _details.starredRepos.asStateFlow()
    val pageNumber = _details.pageNumber.asStateFlow()

    init {
        refresh()
    }


//  starred repo list screen


    private suspend fun loadData() {
            try {
                _details.loadState.value = LoadState.Loading

                _details.starredRepos.value = getStarredRepoListUseCase(
                    perPage = 20,
                    pageNumber = _details.pageNumber.value,
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


//  starred repo list screen navigation


    fun scrollToTop() {
        viewModelScope.launch {
            _details.scrollState.value.scrollToItem(0)
        }
    }

    fun openFirstPage() {
        if (_details.pageNumber.value != 1) {
            _details.pageNumber.value = 1
            scrollToTop()
            refresh()
        }
    }

    fun openPrevPage() {
        if (_details.pageNumber.value != 1) {
            _details.pageNumber.value -= 1
            scrollToTop()
            refresh()
        }
    }

    fun openNextPage() {
        _details.pageNumber.value += 1
        scrollToTop()
        refresh()
    }


//  screen navigation


    fun goToRepo(repoId: Int) {
        viewModelScope.launch {
            navigator.navigate(Destination.RepoPageScreen(repoId))
        }
    }

    fun goBack() {
        viewModelScope.launch {
            navigator.navigateUp()
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