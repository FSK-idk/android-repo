package co.feip.fefu2025.presentation.loading

sealed interface LoadState {
    data object Loading: LoadState

    data object NotLoading: LoadState

    data object Error: LoadState
}