package co.feip.fefu2025.data.service.gitlab_api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class GitLabApi {
    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    val retrofitService: GitLabApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gitlab.com/api/v4/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GitLabApiService::class.java)
    }
}