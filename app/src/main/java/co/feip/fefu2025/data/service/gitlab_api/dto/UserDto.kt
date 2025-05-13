package co.feip.fefu2025.data.service.gitlab_api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("username")
    val username: String? = null,
)