package co.feip.fefu2025.domain.model

import kotlinx.datetime.LocalDate

class RepoPage(
    var name: String,
    var description: String,
    var forkNumber: Int,
    var starNumber: Int,
    var creationDate: LocalDate,
    var langs: Array<Lang>,
    var icon: Int,
)