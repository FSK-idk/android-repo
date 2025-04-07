package co.feip.fefu2025.presentation.repo_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.Lang
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetRepoPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate

class RepoScreenViewModel(
    private val getRepoPageUseCase: GetRepoPageUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
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

    private val _repoIcon = MutableStateFlow<Int>(0)
    val repoIcon = _repoIcon.asStateFlow()

    init {
        updateScreen()
    }

    fun updateScreen() {
        val repo = getRepoPageUseCase()
        _repoName.value = repo.name
        _repoDescription.value = repo.description
        _repoStarNumber.value = repo.starNumber
        _repoForkNumber.value = repo.forkNumber
        _repoCreationDate.value = repo.creationDate
        _repoLangs.value = repo.langs
        _repoIcon.value = repo.icon
    }

    fun formatDecimal(number: Int): String {
        return formatDecimalUseCase(number)
    }

    fun turnDescription() {
        _isDescriptionExpanded.value = !_isDescriptionExpanded.value
    }

    fun onBackClick() {
        Log.d("RepoScreenVM", "back clicked")
    }
}