package co.feip.fefu2025.domain.model

import kotlinx.datetime.LocalDate

class RepoPage(
    val id: Int,
    val name: String,
    val description: String,
    val forkNumber: Int,
    val starNumber: Int,
    val creationDate: LocalDate,
    val langs: List<Lang>,
    val starred: Boolean,
    val iconUrl: String,
)