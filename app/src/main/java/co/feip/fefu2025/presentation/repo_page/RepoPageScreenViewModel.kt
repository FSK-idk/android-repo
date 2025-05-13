package co.feip.fefu2025.presentation.repo_page

import android.content.Context
import android.icu.text.CompactDecimalFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import co.feip.fefu2025.domain.use_case.StarRepoUseCase
import co.feip.fefu2025.domain.use_case.UnstarRepoUseCase
import co.feip.fefu2025.nav.Destination
import co.feip.fefu2025.nav.Navigator
import co.feip.fefu2025.presentation.loading.LoadState
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoPageScreenViewModel(
    private val data: Destination.RepoPageScreen,
    private val appContext: Context,
    private val navigator: Navigator,
    private val getRepoPageUseCase: GetRepoPageUseCase,
    private val starRepoUseCase: StarRepoUseCase,
    private val unstarRepoUseCase: UnstarRepoUseCase,
) : ViewModel() {
    private val _details = RepoPageScreenStates()

    val loadState = _details.loadState.asStateFlow()
    val descriptionExpanded = _details.descriptionExpanded.asStateFlow()
    val repoName = _details.repoName.asStateFlow()
    val repoDescription = _details.repoDescription.asStateFlow()
    val repoStarNumber = _details.repoStarNumber.asStateFlow()
    val repoForkNumber = _details.repoForkNumber.asStateFlow()
    val repoCreationDate = _details.repoCreationDate.asStateFlow()
    val repoLangs = _details.repoLangs.asStateFlow()
    val repoStarred = _details.repoStarred.asStateFlow()
    val repoIconUrl = _details.repoIconUrl.asStateFlow()

    init {
        refresh()
    }


//  repo page screen


    private suspend fun loadData() {
        try {
            _details.loadState.value = LoadState.Loading

            val repo = getRepoPageUseCase(data.repoId)
            _details.repoName.value = repo.name
            _details.repoDescription.value = repo.description
            _details.repoStarNumber.value = repo.starNumber
            _details.repoForkNumber.value = repo.forkNumber
            _details.repoCreationDate.value = repo.creationDate
            _details.repoLangs.value = repo.langs
            _details.repoStarred.value = repo.starred
            _details.repoIconUrl.value = repo.iconUrl

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

    fun turnDescription() {
        _details.descriptionExpanded.value = !_details.descriptionExpanded.value
    }

    fun starRepo() {
        viewModelScope.launch {
            val response =
                if (_details.repoStarred.value) unstarRepoUseCase(data.repoId)
                else starRepoUseCase(data.repoId)

            if (response) {
                _details.repoStarNumber.value += if (_details.repoStarred.value) -1 else 1
                _details.repoStarred.value = !_details.repoStarred.value
            }
        }
    }


//  screen navigation


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