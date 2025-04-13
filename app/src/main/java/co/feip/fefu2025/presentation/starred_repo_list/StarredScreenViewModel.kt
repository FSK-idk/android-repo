package co.feip.fefu2025.presentation.starred_repo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetStarredRepoListUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StarredScreenViewModel(
    private val navigator: Navigator,
    private val getStarredRepoListUseCase: GetStarredRepoListUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _starredRepos = MutableStateFlow<List<Repo>>(listOf())
    val starredRepos = _starredRepos.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true

            _starredRepos.value = getStarredRepoListUseCase()

            _isLoading.value = false
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

    fun onBackClick() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
}