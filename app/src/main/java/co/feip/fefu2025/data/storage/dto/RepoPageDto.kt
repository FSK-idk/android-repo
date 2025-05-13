package co.feip.fefu2025.data.storage.dto

import co.feip.fefu2025.domain.model.RepoPage
import kotlinx.datetime.LocalDate

data class RepoPageDto(
    val id: Int,
    val name: String,
    val description: String,
    val starNumber: Int,
    val forkNumber: Int,
    val creationDate: LocalDate,
    val langs: List<LangDto>,
    val starred: Boolean,
    val iconUrl: String,
)

val RepoPageDto.toDomain
    get() = RepoPage(
        id = id,
        name = name,
        description = description,
        starNumber = starNumber,
        forkNumber = forkNumber,
        creationDate = creationDate,
        langs = langs.map{ it.toDomain },
        starred = starred,
        iconUrl = iconUrl,
    )