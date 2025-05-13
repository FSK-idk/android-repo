package co.feip.fefu2025.data.storage.dto

import co.feip.fefu2025.domain.model.Repo

data class RepoDto(
    val id: Int,
    val name: String,
    val description: String,
    val starNumber: Int,
    val forkNumber: Int,
    val iconUrl: String,
)

val RepoDto.toDomain
    get() = Repo(
        id = id,
        name = name,
        description = description,
        starNumber = starNumber,
        forkNumber = forkNumber,
        iconUrl = iconUrl,
    )