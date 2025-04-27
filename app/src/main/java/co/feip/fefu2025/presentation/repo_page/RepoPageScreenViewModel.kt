package co.feip.fefu2025.presentation.repo_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class RepoPageScreenViewModel(
    private val data: Destination.RepoPageScreen,
    private val navigator: Navigator,
    private val getRepoPageUseCase: GetRepoPageUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.NotLoading)
    val loadState = _loadState.asStateFlow()

    private val _isDescriptionExpanded = MutableStateFlow(false)
    val isDescriptionExpanded = _isDescriptionExpanded.asStateFlow()

    private val _repoName = MutableStateFlow("")
    val repoName = _repoName.asStateFlow()

    private val _repoDescription = MutableStateFlow("")
    val repoDescription = _repoDescription.asStateFlow()

    private val _repoStarNumber = MutableStateFlow(0)
    val repoStarNumber = _repoStarNumber.asStateFlow()

    private val _repoForkNumber = MutableStateFlow(0)
    val repoForkNumber = _repoForkNumber.asStateFlow()

    private val _repoCreationDate = MutableStateFlow<LocalDate>(LocalDate(1970, 1, 1))
    val repoCreationDate = _repoCreationDate.asStateFlow()

    private val _repoLangs = MutableStateFlow<Array<Lang>>(arrayOf())
    val repoLangs = _repoLangs.asStateFlow()

    private val _repoIcon = MutableStateFlow<Int>(R.drawable.ic_launcher_foreground)
    val repoIcon = _repoIcon.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading

                val repo = getRepoPageUseCase(data.repoId)
                _repoName.value = repo.name
                _repoDescription.value = repo.description
                _repoStarNumber.value = repo.starNumber
                _repoForkNumber.value = repo.forkNumber
                _repoCreationDate.value = repo.creationDate
                _repoLangs.value = repo.langs
                _repoIcon.value = repo.icon

                _loadState.value = LoadState.NotLoading
            } catch (e: Exception) {
                _loadState.value = LoadState.Error
            }
        }

    }

    fun formatDecimal(number: Int): String {
        return formatDecimalUseCase(number)
    }

    fun turnDescription() {
        _isDescriptionExpanded.value = !_isDescriptionExpanded.value
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