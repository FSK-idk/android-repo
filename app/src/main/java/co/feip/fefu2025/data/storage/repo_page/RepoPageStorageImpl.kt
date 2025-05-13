package co.feip.fefu2025.data.storage.repo_page

import android.util.Log
import co.feip.fefu2025.app.TAG
import co.feip.fefu2025.data.service.gitlab_api.GitLabApi
import co.feip.fefu2025.data.storage.RepoPageStorage
import co.feip.fefu2025.data.storage.dto.LangDto
import co.feip.fefu2025.data.storage.dto.RepoPageDto
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char

class RepoPageStorageImpl(
    val gitLabApi: GitLabApi,
) : RepoPageStorage {
    override suspend fun get(id: Int): RepoPageDto {
        val getCurrentUserResponse = try {
            gitLabApi.retrofitService.getCurrentUser()
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getCurrentUserResponse.isSuccessful || getCurrentUserResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val getProjectResponse = try {
            gitLabApi.retrofitService.getProject(projectId = id)
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getProjectResponse.isSuccessful || getProjectResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val getProjectLanguagesResponse = try {
            gitLabApi.retrofitService.getProjectLanguages(projectId = id)
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        if (!getProjectLanguagesResponse.isSuccessful || getProjectLanguagesResponse.body() == null) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Internal Error")
        }

        val currentUser = getCurrentUserResponse.body()!!
        val project = getProjectResponse.body()!!
        val projectLanguages = getProjectLanguagesResponse.body()!!

        if (project.id == null) {
            Log.e(TAG, "Project is not found")
            throw Exception("Internal Error")
        }

        if (currentUser.id == null || currentUser.username == null) {
            Log.e(TAG, "Current user is not found")
            throw Exception("Internal Error")
        }

        var starred = false
        var page = 1
        while (!starred) {
            val getProjectStarers = try {
                gitLabApi.retrofitService.getProjectStarrers(
                    projectId = project.id!!,
                    search = currentUser.username,
                    perPage = 100,
                    page = page,
                )
            } catch (e: Exception) {
                Log.e(TAG, "$e")
                throw Exception("Internal Error")
            }

            if (!getProjectStarers.isSuccessful || getProjectStarers.body() == null) {
                Log.e(TAG, "Response is not successful")
                throw Exception("Internal Error")
            }

            val projectStarrers = getProjectStarers.body()!!

            if (projectStarrers.isEmpty()) {
                break
            }

            for (starrer in projectStarrers) {
                if (starrer.user?.id == currentUser.id) {
                    starred = true
                    break
                }
            }

            page += 1
        }

        return RepoPageDto(
            id = project.id ?: -1,
            name = project.name ?: "",
            description = project.description ?: "",
            starNumber = project.starCount ?: -1,
            forkNumber = project.forksCount ?: -1,
            creationDate = parseToLocalDate(project.createdAt),
            langs = projectLanguages.map {
                LangDto(
                    name = it.key,
                    percentage = it.value,
                )
            },
            starred = starred,
            iconUrl = project.avatarUrl ?: "Not found",
        )
    }

    override suspend fun star(id: Int): Boolean {
        val starProjectResponse = try {
            gitLabApi.retrofitService.starProject(
                projectId = id,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }
        Log.d(TAG, "Response: ${starProjectResponse.code() == 201}")

        return starProjectResponse.code() == 201
    }

    override suspend fun unstar(id: Int): Boolean {
        val unstarProjectResponse = try {
            gitLabApi.retrofitService.unstarProject(
                projectId = id,
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        Log.d(TAG, "Response: ${unstarProjectResponse.code() == 201}")

        return unstarProjectResponse.code() == 201
    }

    private fun parseToLocalDate(dateTimeText: String?): LocalDate {
        if (dateTimeText == null) {
            return LocalDate.fromEpochDays(0)
        }

        val customFormat = LocalDateTime.Format {
            year(); char('-'); monthNumber(); char('-'); dayOfMonth();
            char('T');
            hour(); char(':'); minute(); char(':'); second(); char('.'); secondFraction();
            char('Z');
        }

        val date = try {
            customFormat.parse(dateTimeText)
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Internal Error")
        }

        return LocalDate(date.year, date.month, date.dayOfMonth)
    }
}