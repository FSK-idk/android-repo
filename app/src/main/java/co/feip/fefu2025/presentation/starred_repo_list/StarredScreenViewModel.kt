package co.feip.fefu2025.presentation.starred_repo_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StarredScreenViewModel(
    private val navigator: Navigator,
    private val getStarredRepoListUseCase: GetStarredRepoListUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val loadState = _loadState.asStateFlow()

    private val _starredRepos = MutableStateFlow<List<Repo>>(listOf())
    val starredRepos = _starredRepos.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        Log.d("LLLLL", "LOADDDDDD")
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading

                _starredRepos.value = getStarredRepoListUseCase()

                _loadState.value = LoadState.NotLoading
            } catch (e: Exception) {
                _loadState.value = LoadState.Error
            }
        }
    }

    fun formatDecimal(number: Int): String {
        return formatDecimalUseCase(number)
    }

    fun onRepoClick(repoId: Int) {
        viewModelScope.launch {
            navigator.navigate(Destination.RepoPageScreen(repoId))
        }
    }

    fun onRetryClick() {
        loadData()
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
}