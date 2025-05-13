package co.feip.fefu2025.data.storage.dto

import co.feip.fefu2025.domain.model.Lang

data class LangDto(
    val name: String,
    val percentage: Float,
)

val LangDto.toDomain
    get() = Lang(
        name = name,
        percentage = percentage,
    )