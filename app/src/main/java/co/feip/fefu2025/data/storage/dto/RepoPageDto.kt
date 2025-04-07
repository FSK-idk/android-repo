package co.feip.fefu2025.data.storage.dto

import kotlinx.datetime.LocalDate

class RepoPageDto(
    var name: String,
    var description: String,
    var starNumber: Int,
    var forkNumber: Int,
    var creationDate: LocalDate,
    var langs: Array<LangDto>,
    var icon: Int,
)