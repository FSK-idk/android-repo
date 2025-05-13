package co.feip.fefu2025.data.storage.repo_list

import android.util.Log
import co.feip.fefu2025.app.TAG
import co.feip.fefu2025.data.service.gitlab_api.GitLabApi
import co.feip.fefu2025.data.storage.RepoListStorage
import co.feip.fefu2025.data.storage.dto.RepoDto

class RepoListStorageImpl(
    val gitLabApi: GitLabApi,
) : RepoListStorage {
    override suspend fun getStarredList(
        perPage: Int,
        pageNumber: Int
    ): List<RepoDto> {
        val getProjectsResponse = try {
            gitLabApi.retrofitService.getProjects(
                starred = true,
                orderBy = "last_activity_at",
                perPage = perPage,
                page = pageNumber,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getProjectsResponse.isSuccessful || getProjectsResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val projects = getProjectsResponse.body()!!

        return projects
            .take(10)
            .map {
                RepoDto(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    starNumber = it.starCount ?: -1,
                    forkNumber = it.forksCount ?: -1,
                    iconUrl = it.avatarUrl ?: "Not found",
                )
            }
    }

    override suspend fun getPopularList(
        perPage: Int,
        pageNumber: Int
    ): List<RepoDto> {
        val getProjectsResponse = try {
            gitLabApi.retrofitService.getProjects(
                orderBy = "star_count",
                perPage = perPage,
                page = pageNumber,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getProjectsResponse.isSuccessful || getProjectsResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val projects = getProjectsResponse.body()!!

        return projects
            .take(10)
            .map {
                RepoDto(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    starNumber = it.starCount ?: -1,
                    forkNumber = it.forksCount ?: -1,
                    iconUrl = it.avatarUrl ?: "Not found",
                )
            }
    }

    override suspend fun getRepoListByName(
        name: String,
        perPage: Int,
        pageNumber: Int
    ): List<RepoDto> {
        val getProjectsResponse = try {
            gitLabApi.retrofitService.getProjects(
                search = name,
                orderBy = "star_count",
                perPage = perPage,
                page = pageNumber,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getProjectsResponse.isSuccessful || getProjectsResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val projects = getProjectsResponse.body()!!

        return projects
            .map {
                RepoDto(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    starNumber = it.starCount ?: -1,
                    forkNumber = it.forksCount ?: -1,
                    iconUrl = it.avatarUrl ?: "Not found",
                )
            }
    }
}