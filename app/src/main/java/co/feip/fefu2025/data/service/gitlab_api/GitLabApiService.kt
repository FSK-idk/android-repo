package co.feip.fefu2025.data.service.gitlab_api

import co.feip.fefu2025.BuildConfig
import co.feip.fefu2025.data.service.gitlab_api.dto.UserDto
import co.feip.fefu2025.data.service.gitlab_api.dto.ProjectDto
import co.feip.fefu2025.data.service.gitlab_api.dto.StarrerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GitLabApiService {
    @GET("user")
    suspend fun getCurrentUser(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
    ): Response<UserDto>

    @GET("projects")
    suspend fun getProjects(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Query("search") search: String? = null,
        @Query("starred") starred: Boolean? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("page") page: Int? = null,
    ): Response<List<ProjectDto>>

    @GET("projects/{project_id}")
    suspend fun getProject(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Path("project_id") projectId: Int,
    ): Response<ProjectDto>

    @GET("projects/{project_id}/starrers")
    suspend fun getProjectStarrers(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Path("project_id") projectId: Int,
        @Query("search") search: String? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("page") page: Int? = null,
    ): Response<List<StarrerDto>>

    @POST("projects/{project_id}/star")
    suspend fun starProject(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Path("project_id") projectId: Int,
    ): Response<Unit>

    @POST("projects/{project_id}/unstar")
    suspend fun unstarProject(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Path("project_id") projectId: Int,
    ): Response<Unit>

    @GET("projects/{project_id}/languages")
    suspend fun getProjectLanguages(
        @Header("PRIVATE-TOKEN") privateToken: String = BuildConfig.API_KEY,
        @Path("project_id") projectId: Int,
    ): Response<Map<String, Float>>
}