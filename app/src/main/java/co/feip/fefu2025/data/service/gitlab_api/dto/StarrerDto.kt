package co.feip.fefu2025.data.service.gitlab_api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarrerDto(
    @SerialName("user")
    val user: UserDto? = null,
)