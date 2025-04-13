package co.feip.fefu2025.data.storage.dto

import kotlinx.datetime.LocalDate

class RepoPageDto(
    val id: Int,
    val name: String,
    val description: String,
    val starNumber: Int,
    val forkNumber: Int,
    val creationDate: LocalDate,
    val langs: Array<LangDto>,
    val icon: Int,
)