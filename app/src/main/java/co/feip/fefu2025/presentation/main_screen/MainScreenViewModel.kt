package co.feip.fefu2025.presentation.main_screen

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.domain.use_case.GetPopularReposUseCase
import co.feip.fefu2025.domain.use_case.GetStarredReposUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
    private val getStarredReposUseCase: GetStarredReposUseCase,
    private val getPopularReposUseCase: GetPopularReposUseCase,
    private val formatDecimalUseCase: FormatDecimalUseCase,
) : ViewModel() {
    private val _starredRepos = MutableStateFlow<Array<Repo>>(arrayOf())
    val starredRepos = _starredRepos.asStateFlow()

    private val _popularRepos = MutableStateFlow<Array<Repo>>(arrayOf())
    val popularRepos = _popularRepos.asStateFlow()

    init {
        updateScreen()
    }

    fun updateScreen() {
        _starredRepos.value = getStarredReposUseCase()
        _popularRepos.value = getPopularReposUseCase()
    }

    fun formatDecimal(number: Int): String {
        return formatDecimalUseCase(number)
    }
}