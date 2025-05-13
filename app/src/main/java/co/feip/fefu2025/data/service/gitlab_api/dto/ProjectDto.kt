package co.feip.fefu2025.data.service.gitlab_api.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ProjectDto(
    @SerialName("id")
    var id: Int? = null,

    @SerialName("name")
    var name: String? = null,

    @SerialName("description")
    var description: String? = null,

    @SerialName("star_count")
    var starCount: Int? = null,

    @SerialName("forks_count")
    var forksCount: Int? = null,

    @SerialName("created_at")
    var createdAt: String? = null,

    @SerialName("avatar_url")
    var avatarUrl: String? = null,
)