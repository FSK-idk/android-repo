package co.feip.fefu2025.nav

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object RepoListScreen: Destination

    @Serializable
    data object StarredRepoListScreen: Destination

    @Serializable
    data class RepoPageScreen(val repoId: Int): Destination
}